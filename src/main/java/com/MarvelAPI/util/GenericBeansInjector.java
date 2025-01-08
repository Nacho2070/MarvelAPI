package com.MarvelAPI.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class GenericBeansInjector {
    @Bean
    RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
