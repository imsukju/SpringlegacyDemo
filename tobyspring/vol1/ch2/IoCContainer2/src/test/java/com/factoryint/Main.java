package com.factoryint;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

        ClientService clientService = context.getBean(ClientService.class);
        // ClientService 사용
        clientService.doSomething();
    }
}