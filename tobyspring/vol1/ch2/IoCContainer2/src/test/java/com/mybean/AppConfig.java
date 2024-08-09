package com.mybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public MyBean myBean() {
        MyBean myBean = new MyBean();
        myBean.setName("John Doe");
        myBean.setAge(30);
        return myBean;
    }
	
    @Bean
    public LegacyConnectionPool legacyConnectionPool() {
        return new LegacyConnectionPool("jdbc:legacy-db://localhost:3306/mydb");
    }
}
