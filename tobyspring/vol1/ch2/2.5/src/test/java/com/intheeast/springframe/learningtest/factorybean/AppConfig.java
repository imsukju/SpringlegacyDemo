package com.intheeast.springframe.learningtest.factorybean;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
	
	@Bean
	public MyCustomObjectFactoryBean MyCustomObjectFactory()
	{
		MyCustomObjectFactoryBean factoryBean = new MyCustomObjectFactoryBean();
		return factoryBean;
	}
	
	@Bean
	public MyCustomObject myCustomObject() throws Exception
	{
		return MyCustomObjectFactory().getObject();
	}

}
