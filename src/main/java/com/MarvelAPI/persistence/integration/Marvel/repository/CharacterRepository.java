package com.MarvelAPI.persistence.integration.Marvel.repository;

import com.MarvelAPI.persistence.integration.Marvel.Dto.CharacterDto;
import com.MarvelAPI.persistence.integration.Marvel.MarvelAPIConfig;
import com.MarvelAPI.persistence.integration.Marvel.mapper.CharacterMapper;
import com.MarvelAPI.services.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class CharacterRepository {
    @Autowired
    private HttpClientService httpClientService;

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;

    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String characterPath;

    @PostConstruct
    public void setPath(){
        this.characterPath = basePath.concat("/").concat("characters");
    }

    public List<CharacterDto> findAll(int[] series, int[] comic, int limit, String name){
        Map<String,String> marvelQueryParams = this.getQueryParamsFindAll(series,comic,limit,name);

        JsonNode response = httpClientService.doGet(characterPath,marvelQueryParams,JsonNode.class);

        return CharacterMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsFindAll(int[] series, int[] comic, int limit, String name) {
        Map<String, String> queryParams = marvelAPIConfig.getAuthenticationQueryParams();

        queryParams.put("offset", "0");
        queryParams.put("limit",Integer.toString(limit));

      if (StringUtils.hasText(name)){
          queryParams.put("name",name);
      }
      if(series != null){
          String seriesStr = joinIntArray(series);
        queryParams.put("series",seriesStr);
      }
      if(comic != null){
          String comicStr = joinIntArray(comic);
          queryParams.put("comics",comicStr);
      }
      return queryParams;
    }
    private String joinIntArray(int[] comics) {
        List<String> list = IntStream.of(comics).boxed()
                .map(each -> each.toString())
                .toList();

        return String.join(",", list);
    }

}
