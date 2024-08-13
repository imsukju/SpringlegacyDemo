package com.stvalue1.anotherbean.parentchild;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ParentConfig {

    @Bean
    public AccountService accountService() {
        return new SimpleAccountService();
    }
}