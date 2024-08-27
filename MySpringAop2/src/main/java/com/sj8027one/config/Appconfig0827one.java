package com.sj8027one.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultIntroductionAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.sj8027one.advice.LoggingBeforeAdvice0827one;
import com.sj8027one.service.SimpleService0827one;
import com.sj8027one.service.SimpleService0827oneImple;

@Configuration
public class Appconfig0827one {
	
	@Bean
	public SimpleService0827one simpleservice()
	{
		return new SimpleService0827oneImple();
	}
	
	@Bean
	public LoggingBeforeAdvice0827one loggingadivce()
	{
		return new LoggingBeforeAdvice0827one();
	}
	
	@Bean
	public DefaultIntroductionAdvisor loggingAdvisor()
	{
		return new DefaultIntroductionAdvisor(loggingadivce());
	}
	
	
	@Bean 
	public ProxyFactoryBean proxyFactoryBean() 
	{
		ProxyFactoryBean proxyfactoybean = new ProxyFactoryBean();
		proxyfactoybean.setTarget(simpleservice());
		proxyfactoybean.setProxyTargetClass(true);
		
		//setOptimize 를 true 로 하면 cglib이 된다 setProxyTargetClass(True)로 설정됨
		proxyfactoybean.setOptimize(true);
		proxyfactoybean.setFrozen(true);
		
		//
		proxyfactoybean.setExposeProxy(true);
		proxyfactoybean.setInterfaces(new Class<?>[] {SimpleService0827one.class});
		proxyfactoybean.setInterceptorNames("loggingAdvisor");
		proxyfactoybean.setSingleton(false);
		return proxyfactoybean;
		
	}
	
	@Bean 
	@Lazy
	public SimpleService0827one proxySimpleService()
	{
		return (SimpleService0827one)proxyFactoryBean().getObject();
	}

}
