package com.AspectjDemo.One.declareingpointcut.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import com.AspectjDemo.One.declareingpointcut.service.SpecialService;
import com.AspectjDemo.One.declareingpointcut.service.TransferService;

@Configuration
@ComponentScan(basePackages = "com.AspectjDemo.One.declareingpointcut") // Component 어노테이션이 적용된걸 찾는다
@EnableAspectJAutoProxy
public class AppConfigOne {
	
	
	
	@Bean
	public SpecialService specialService()
	{
		return new SpecialService();
	}
	
	@Bean
	public TransferService transferService()
	{
		return new TransferService();
	}
	
}
