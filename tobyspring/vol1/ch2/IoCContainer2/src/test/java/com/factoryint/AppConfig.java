package com.factoryint;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public DefaultServiceLocator serviceLocator() {
        return new DefaultServiceLocator();
    }

    @Bean
    public ClientService clientService() {
        return serviceLocator().createClientServiceInstance();
    }
}