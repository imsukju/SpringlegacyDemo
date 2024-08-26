package com.sj0826.dynamicpointcuts.config;


import org.aopalliance.aop.Advice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.sj0826.dynamicpointcuts.advice.SimpleDynamicAdvice;
import com.sj0826.dynamicpointcuts.caller.TaskCaller;
import com.sj0826.dynamicpointcuts.service.SimpleDynamicPointService;

@Configuration
public class AppDynamicPointutConfig {
	@Bean
	public SimpleDynamicPointService simpleService()
	{
		return new SimpleDynamicPointService();
	}
	

	@Bean
	public Advice simpleadvice()
	{
		return new SimpleDynamicAdvice();
	}
	
	
	//Construct a new pointcut that matches all calls below a method matchingthe given method name pattern in the given class. 
	@Bean
	public Pointcut controlFolwPointcut()
	{
		return new ControlFlowPointcut(TaskCaller.class, ".*call.*");
	}
	
	@Bean
	public DefaultPointcutAdvisor defaultpoint()
	{
		return new DefaultPointcutAdvisor(controlFolwPointcut(), simpleadvice());
	}
	
	@Bean
	@Lazy
	public ProxyFactoryBean proxyFB()
	{
		ProxyFactoryBean proxyyfa = new ProxyFactoryBean();
		proxyyfa.setTarget(simpleService());
		proxyyfa.addAdvisor(defaultpoint());
		return proxyyfa;
	}
	
	@Bean
	public TaskCaller taskCaller()
	{
		return new TaskCaller(simpleService());
	}
	
}
