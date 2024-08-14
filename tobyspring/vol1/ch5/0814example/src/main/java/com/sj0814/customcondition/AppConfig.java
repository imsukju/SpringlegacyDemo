package com.sj0814.customcondition;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    @Conditional(CustomCondition.class)
    public MyService myService() {
        return new MyServiceImpl();
    }
}
