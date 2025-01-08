package com.MarvelAPI.services;


import java.util.Map;

public interface HttpClientService {
    <T> T doGet(String url, Map<String, String> queryParams, Class<T> classType);
    <T, R> T doPost(String url,Map<String,String> queryParams,Class<T> classType, R bodyRequest);
    <T, R> T doPut(String url,Map<String,String> queryParams,Class<T> classType, R bodyRequest);
    <T> T doDelete(String url,Map<String,String> queryParams,Class<T> classType);

}
