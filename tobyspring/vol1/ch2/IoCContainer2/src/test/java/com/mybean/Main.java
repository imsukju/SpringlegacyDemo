package com.mybean;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
    	AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    	MyBean exampleBean = context.getBean("myBean",MyBean.class);
        LegacyConnectionPool exampleBean2 = context.getBean("legacyConnectionPool",LegacyConnectionPool.class);

        // ExampleBean 및 ExampleBeanTwo 사용
        exampleBean.getAge();
        exampleBean2.connect();
    }
}
