package com.MarvelAPI.services.impl;

import com.MarvelAPI.services.HttpClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Map;

@Service
public class RestTemplateService implements HttpClientService {
    @Autowired
    private RestTemplate restTemplate;

    @Override
    public <T> T doGet(String url, Map<String, String> queryParams, Class<T> classType) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            builder.queryParam(entry.getKey(),entry.getValue());
        }
        String finalUrl = builder.build().toUriString();
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.GET, null, classType);
        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            throw new RuntimeException(response.getStatusCode().toString());
        }

        return response.getBody();

    }

    @Override
    public <T, R> T doPost(String url, Map<String, String> queryParams, Class<T> classType, R bodyRequest) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(url);
        for (Map.Entry<String, String> entry : queryParams.entrySet()) {
            builder.queryParam(entry.getKey(),entry.getValue());
        }
        String finalUrl = builder.build().toUriString();
        ResponseEntity<T> response = restTemplate.exchange(finalUrl, HttpMethod.POST, (HttpEntity<?>) bodyRequest, classType);
        if (response.getStatusCode().value() != HttpStatus.OK.value()) {
            throw new RuntimeException(response.getStatusCode().toString());
        }


        return null;
    }

    @Override
    public <T, R> T doPut(String url, Map<String, String> queryParams, Class<T> classType, R bodyRequest) {
        return null;
    }

    @Override
    public <T> T doDelete(String url, Map<String, String> queryParams, Class<T> classType) {
        return null;
    }
}
