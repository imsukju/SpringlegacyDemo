package com.sj.pointcut1.config;

import org.springframework.aop.Pointcut;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

import com.sj.pointcut1.advice.ExeceptionHandlingAdvice;
import com.sj.pointcut1.advice.ExecutionTimeAdvice;
import com.sj.pointcut1.advice.LoggingAdvice;
import com.sj.pointcut1.pointcut.CumstomPointCut;
import com.sj.pointcut1.service.AnotherService;
import com.sj.pointcut1.service.Myservice;

@Configuration
public class AppConfig {
	@Bean
	public Myservice myService()
	{
		return new Myservice();
	}
	@Bean
	public AnotherService anotherService()
	{
		return new AnotherService();
	}
	
	
	@Qualifier("customPointCut")
	@Bean
	public Pointcut customPointCut()
	{
		return new CumstomPointCut();
	}
	@Bean
	public Pointcut aspectJPointcut()
	{
		AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
		pointcut.setExpression("excution(*com.sj.pointcut1.service.Anotherservice.myMethod(..))");
		return pointcut;
	}
	
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor aspectjPointcutAdvisor(@Qualifier("aspectJPointcut") Pointcut pointcut)
	{
		return new DefaultPointcutAdvisor(pointcut, new ExeceptionHandlingAdvice());
	}
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor loggingAdvisor(@Qualifier("customPointCut") Pointcut pointcut)
	{
		return new DefaultPointcutAdvisor(pointcut, new LoggingAdvice());
	}
	@Lazy
	@Bean
	public DefaultPointcutAdvisor excutionTimeAdvisor(@Qualifier("customPointCut") Pointcut pointcut)
	{
		return new DefaultPointcutAdvisor(pointcut, new ExecutionTimeAdvice());
	}
	
	
	@Lazy
	@Bean
	public DefaultPointcutAdvisor exceptionHandlingAdvisor(@Qualifier("customPointCut") Pointcut pointcut)
	{
		return new DefaultPointcutAdvisor(pointcut, new ExeceptionHandlingAdvice());
	}
	

	@Lazy
	@Bean
	public ProxyFactoryBean myServiceProxy(Myservice myService, 
			 @Qualifier("loggingAdvisor") DefaultPointcutAdvisor loggingAdvisor,
			 @Qualifier("excutionTimeAdvisor") DefaultPointcutAdvisor excutionTimeAdvisor,
			 @Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor) 
	{
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(myService);
		proxyFactoryBean.setInterceptorNames("loggingAdvisor","excutionTimeAdvisor","exceptionHandlingAdvisor");
		
		return proxyFactoryBean;

	}
	@Lazy
	@Bean
	public ProxyFactoryBean anotherserivceProxy(AnotherService anotherService, 
			 @Qualifier("aspectjPointcutAdvisor") DefaultPointcutAdvisor loggingAdvisor,
			 @Qualifier("excutionTimeAdvisor") DefaultPointcutAdvisor excutionTimeAdvisor,
			 @Qualifier("exceptionHandlingAdvisor") DefaultPointcutAdvisor exceptionHandlingAdvisor) 
	{
		ProxyFactoryBean proxyFactoryBean = new ProxyFactoryBean();
		proxyFactoryBean.setTarget(anotherService);
		proxyFactoryBean.setInterceptorNames("loggingAdvisor","excutionTimeAdvisor","exceptionHandlingAdvisor");
		
		return proxyFactoryBean;

	}
}
