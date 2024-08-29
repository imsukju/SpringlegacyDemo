package com.AspectjDemo.One.declareingpointcut.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@Qualifier("hello")
@ComponentScan(basePackages = "com.AspectjDemo.One.declareingpointcut") // Component 어노테이션이 적용된걸 찾는다
public class AppConfigOne {

	
}
