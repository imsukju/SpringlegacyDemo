package com.sj8027.ManipulatingAdvised.config;

import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sj8027.ManipulatingAdvised.advice.DenuginterceptorMpAdvised;
import com.sj8027.ManipulatingAdvised.advice.MpAdvisedUntercepor;
import com.sj8027.ManipulatingAdvised.pointcut.MpAdvisedSpecialPointcut;
import com.sj8027.ManipulatingAdvised.service.Myservice0827Madvised;
import com.sj8027.ManipulatingAdvised.service.Myservice0827MadvisedImpl;

@Configuration
public class MpadvisedAppConfig {
	@Bean
	public Myservice0827Madvised myService()
	{
		return new Myservice0827MadvisedImpl();
	}
	
	@Bean
	public DenuginterceptorMpAdvised debugInterceptor()
	{
		return new DenuginterceptorMpAdvised();
	}
	
	@Bean
	public MpAdvisedUntercepor antoherInterceptor()
	{
		return new MpAdvisedUntercepor();
	}
	
	@Bean
	public ProxyFactoryBean myServiceProxy(Myservice0827Madvised myService, DenuginterceptorMpAdvised debugInterceptor)
	{
		ProxyFactoryBean proxyfactorybean = new ProxyFactoryBean();
		proxyfactorybean.setTarget(myService);
		proxyfactorybean.addAdvice(debugInterceptor);
		proxyfactorybean.setProxyTargetClass(true);
		return proxyfactorybean;
	}
	
	@Bean 
	public MpAdvisedSpecialPointcut myspecialPointcut()
	{
		return new MpAdvisedSpecialPointcut();
	}
	
	@Bean
	public DefaultPointcutAdvisor myAdvisor(MpAdvisedSpecialPointcut myspecialPointcut, DenuginterceptorMpAdvised debugInterceptor)
	{
		return new DefaultPointcutAdvisor(myspecialPointcut, debugInterceptor);
	}
	
	@Bean
	public DefaultPointcutAdvisor anotherAdvisor(MpAdvisedSpecialPointcut myspecialPointcut, MpAdvisedUntercepor antoherInterceptor)
	{
		return new DefaultPointcutAdvisor(myspecialPointcut, antoherInterceptor);
	}
	
}
