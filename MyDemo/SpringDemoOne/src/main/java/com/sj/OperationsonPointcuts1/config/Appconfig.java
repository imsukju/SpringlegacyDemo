package com.sj.OperationsonPointcuts1.config;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.weaver.ast.Or;
import org.springframework.aop.Pointcut;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.Pointcuts;
import org.springframework.aop.support.StaticMethodMatcherPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.sj.OperationsonPointcuts1.service.SimpleService;

@Configuration
@Aspect
public class Appconfig 
{
	private static Pointcut pointcutForMehodA ()
	{
		return new StaticMethodMatcherPointcut() {
	
			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				// TODO Auto-generated method stub
				return "methodA".equals(method.getName());
			}
		};
	}
	
	private static Pointcut pointcutForMehodB ()
	{
		return new StaticMethodMatcherPointcut() {
	
			@Override
			public boolean matches(Method method, Class<?> targetClass) {
				// TODO Auto-generated method stub
				return "methodB".equals(method.getName());
			}
		};
	}
	
	//Or연산임
	private static Pointcut unionPointcut()
	{		   //pointcuts 유틸리티
		return Pointcuts.union(pointcutForMehodA(), pointcutForMehodB());
	}
	
	//And 연산임
	private static Pointcut intersectionPointcut()
	{
		return Pointcuts.intersection(pointcutForMehodA(), pointcutForMehodB());
	}
	
	@Bean
	public SimpleService simpleService()
	{
		ProxyFactoryBean proxyFacotyBean = new ProxyFactoryBean();
		proxyFacotyBean.setTarget(new SimpleService());
		
		org.aopalliance.aop.Advice advice = new MethodInterceptor() {
			
			@Override
			public Object invoke(MethodInvocation invocation) throws Throwable {
				// TODO Auto-generated method stub
				System.out.println("Additional advice before unionPointcut method execution");
				return invocation.proceed();
			}
		};
		
		DefaultPointcutAdvisor advisor = new DefaultPointcutAdvisor(unionPointcut(),advice);
		
		proxyFacotyBean.addAdvisor(advisor);
		
		
		return (SimpleService) proxyFacotyBean.getObject();
	}
}
