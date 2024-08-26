package com.sj0826.conveniencepointcutimplementations.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.aop.support.RegexpMethodPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import com.sj0826.conveniencepointcutimplementations.advice.SimpeConvenienceAdvice;
import com.sj0826.conveniencepointcutimplementations.service.MyServiceConveniencePointcut;
import com.sj0826.conveniencepointcutimplementations.service.SimpleConvenienceService;

@Configuration
public class AppconfigConvenience2 {

	@Bean
	public SimpleConvenienceService simpleService()
	{
		return new SimpleConvenienceService();
	}
	
	@Bean
	public SimpeConvenienceAdvice SimpleAdvice()
	{
		return new SimpeConvenienceAdvice();
	}
	
	@Bean
	public JdkRegexpMethodPointcut jdkRegexMethod()
	{
		JdkRegexpMethodPointcut pointcut = new JdkRegexpMethodPointcut();
		pointcut.setPattern(".*get.*");
		return pointcut;

	}
	
	@Bean
	public DefaultPointcutAdvisor defaultPointcutAdvisor()
	{
		return new DefaultPointcutAdvisor(jdkRegexMethod(),SimpleAdvice());
	}
	
	@Bean RegexpMethodPointcutAdvisor setterAndAbsquatuateAdvisor()
	{
		RegexpMethodPointcutAdvisor advior = new RegexpMethodPointcutAdvisor();
		advior.setAdvice(SimpleAdvice());
		advior.setPatterns(",*set.*",".*absabsquatulate.*");
		return advior;
	}
	
	@Bean 
	public ProxyFactoryBean proxyFactoryBean()
	{
		ProxyFactoryBean proxyFacorybean = new ProxyFactoryBean();
		proxyFacorybean.setTarget(SimpleConvenienceService.class);
		proxyFacorybean.addAdvisor(defaultPointcutAdvisor());
		proxyFacorybean.addAdvisor(setterAndAbsquatuateAdvisor());
		
		return proxyFacorybean;
	}
}
