package com.MarvelAPI.persistence.integration.Marvel.repository;

import ch.qos.logback.core.util.StringUtil;
import com.MarvelAPI.persistence.integration.Marvel.Dto.CharacterDto;
import com.MarvelAPI.persistence.integration.Marvel.MarvelAPIConfig;
import com.MarvelAPI.persistence.integration.Marvel.mapper.CharacterMapper;
import com.MarvelAPI.services.HttpClientService;
import com.fasterxml.jackson.databind.JsonNode;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

@Component
public class CharacterRepository {
    private HttpClientService httpClientService;

    @Autowired
    private MarvelAPIConfig marvelAPIConfig;
    @Value("${integration.marvel.base-path}")
    private String basePath;

    private String characterPath;

    @PostConstruct
    public void setPath(){
        characterPath = basePath.concat("/").concat("characters");
    }

    public List<CharacterDto> findAll(int[] series, int[] comic, int limit, String name){
        Map<String,String> marvelQueryParams = getQueryParamsFindAll(series,comic,limit,name);

        JsonNode response = httpClientService.doGet(basePath,marvelQueryParams,JsonNode.class);

        return CharacterMapper.toDtoList(response);
    }

    private Map<String, String> getQueryParamsFindAll(int[] series, int[] comic, int limit, String name) {
    Map<String, String> queryParams = marvelAPIConfig.getAuthtenticationQueryParams();
        queryParams.put("limit",Integer.toString(limit));
      if (StringUtils.hasText(name)){
          queryParams.put("name",name);
      }
      if(series != null){
        queryParams.put("series",joinIntArray(series));
      }
      if(comic != null){
          queryParams.put("comic",joinIntArray(comic));
      }

      return queryParams;
    }
    private String joinIntArray(int[] comics) {
        List<String> list = IntStream.of(comics).boxed()
                .map(each -> Integer.toString(each))
                .toList();

        return String.join(",", list);
    }

}
