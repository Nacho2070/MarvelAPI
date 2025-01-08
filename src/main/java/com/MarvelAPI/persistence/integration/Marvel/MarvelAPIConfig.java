package com.MarvelAPI.persistence.integration.Marvel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class MarvelAPIConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;

    private long timeStamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;

    private String getHash(){
        String hashCode = Long.toString(timeStamp).concat(publicKey).concat(privateKey);
        return md5Encoder.encode(hashCode);
    }
    public Map<String,String> getAuthtenticationQueryParams(){
        Map<String,String> params = new HashMap<String,String>();
        params.put("ts", Long.toString(timeStamp));
        params.put("apiKey", publicKey);
        params.put("hash", getHash());
        return params;
    }
}
