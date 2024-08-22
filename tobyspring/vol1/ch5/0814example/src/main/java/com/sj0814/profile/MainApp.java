package com.sj0814.profile;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import javax.sql.DataSource;

import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
public class MainApp {
	public static void main(String[] args) {

        // 프로파일 설정: "dev" 또는 "prod"
        System.setProperty("spring.profiles.active", "dev");

        ApplicationContext ctx = new AnnotationConfigApplicationContext(AppConfig.class);

        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println("Using DataSource: " + dataSource.getClass().getName());
    }
}