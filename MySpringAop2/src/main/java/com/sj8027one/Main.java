package com.sj8027one;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.framework.Advised;
import org.springframework.aop.framework.ProxyFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj8027one.config.Appconfig0827one;
import com.sj8027one.service.SimpleService0827one;
import com.sj8027one.service.SimpleService0827oneImple;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(Appconfig0827one.class);
		
		SimpleService0827one simple = (SimpleService0827one)context.getBean("proxyFactoryBean");
		
		simple.dosomething();
		
		try
		{
			SimpleService0827one currentproxy = (SimpleService0827one)context.getBean("proxySimpleService");
			System.out.println("currentproxy Class :" + currentproxy.getClass().getName());
		}
		catch (IllegalStateException e)
		{
			System.out.println("호출실패" + e.getMessage());
		}
		
		System.out.println("simple Class :" + simple.getClass().getName());
		
		
		if (simple instanceof SimpleService0827oneImple)
		{
			System.out.println("CGLIB 이 적용됨");
		}
		else
		{
			System.out.println("JDK 이 적용됨");
		}
		
		SimpleService0827one anothersimple = (SimpleService0827one)context.getBean("proxySimpleService");
		System.out.println("anothersimple Class :" + anothersimple.getClass().getName());
		
		if (simple == anothersimple)
		{
			System.out.println("동일한 프록시 인스턴스");
		}
		else
		{
			System.out.println("다른 인스턴스");
		}
		

		
//		try
//		{
//			ProxyFactoryBean proxyFactoryBean = (ProxyFactoryBean) context.getBean("&proxySimpleService");
//			Advised advised = (Advised) proxyFactoryBean.getObject();
//			
//			advised.addAdvice(new MethodBeforeAdvice() {
//				
//				@Override
//				public void before(Method method, Object[] args, Object target) throws Throwable {
//					System.out.println();
//					// TODO Auto-generated method stub
//					
//				}
//			};
//					);
//
//		}
//		catch(Exception e)
//		{
//			e.getMessage();
//		}
		
		
		
	}

}
