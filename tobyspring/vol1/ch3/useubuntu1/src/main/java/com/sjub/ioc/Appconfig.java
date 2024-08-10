package com.sjub.ioc;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Appconfig {
	@Bean
	public CustomObjectFactoryBean coustumobjectbean()
	{
		return new CustomObjectFactoryBean();
	}
	
	@Bean
	public CustomObject customobect() throws Exception
	{
		return coustumobjectbean().getObject();
	}
	
}
