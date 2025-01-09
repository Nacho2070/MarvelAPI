package com.MarvelAPI.persistence.integration.Marvel;

import org.aspectj.lang.annotation.After;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class MarvelAPIConfig {

    @Autowired
    @Qualifier("md5Encoder")
    private PasswordEncoder md5Encoder;

    private final long timeStamp = new Date(System.currentTimeMillis()).getTime();

    @Value("${integration.marvel.public-key}")
    private String publicKey;
    @Value("${integration.marvel.private-key}")
    private String privateKey;

    private String getHash(){
        String hashCode = Long.toString(timeStamp).concat(publicKey).concat(privateKey);
        return md5Encoder.encode(hashCode);
    }

    public Map<String,String> getAuthenticationQueryParams() {
        Map<String, String> securityParams = new LinkedHashMap<>();
        securityParams.put("ts", Long.toString(timeStamp));
        securityParams.put("apiKey", publicKey);
        securityParams.put("hash", this.getHash());
        for (Map.Entry<String, String> entry : securityParams.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        return securityParams;
    }
}
