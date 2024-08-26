package com.sj0826.conveniencepointcutimplementations;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj0826.conveniencepointcutimplementations.config.AppconfigConvenience2;
import com.sj0826.conveniencepointcutimplementations.service.SimpleConvenienceService;

public class Program {

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppconfigConvenience2.class);
		
		SimpleConvenienceService service = (SimpleConvenienceService)context.getBean("proxyFactoryBean");
		
		service.setName("john");
		service.getName();
		service.absquatulate();
		service.performTask();
		context.close();
	}

}
