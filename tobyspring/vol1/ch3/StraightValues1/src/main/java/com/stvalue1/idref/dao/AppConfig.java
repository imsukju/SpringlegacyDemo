package com.stvalue1.idref.dao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public TargetBean theTargetBean() {
        return new TargetBean();
    }

    @Bean
    public ClientBean theClientBean() {
        ClientBean client = new ClientBean();
        client.setTargetName("theTargetBean");  // 직접 ID를 설정
        return client;
    }
}