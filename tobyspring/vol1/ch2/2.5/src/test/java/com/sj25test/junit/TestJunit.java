package com.sj25test.junit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.intheeast.springframe.learningtest.factorybean.MyCustomObject;
import com.intheeast.springframe.learningtest.factorybean.MyCustomObjectFactoryBean;

@Configuration
public class TestJunit {
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
