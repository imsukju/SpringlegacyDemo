package com.sj.pointcut1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj.pointcut1.config.AppConfig;
import com.sj.pointcut1.config.AppConfigForEnableAspectJAutoProxy;
import com.sj.pointcut1.service.AnotherService;
import com.sj.pointcut1.service.Myservice;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
		
		Myservice myservice = (Myservice) context.getBean("myServiceProxy");
		AnotherService anotherService = (AnotherService) context.getBean("anotherserivceProxy");
		
		myservice.myMethod();
		myservice.anotherMEthod("test");
		anotherService.differentMethod(3);
		
		try 
		{
			myservice.methodWithException();
			
		} catch (Exception e)
		{
			System.out.println("Exception handled in main");
		}
		
		System.out.println("Start AutoProxyBean \n\n\n\n\n\n\n");
		execAppconfigForEnableAspectAutoProxy();
	}
	
	
	public static void execAppconfig()
	{
		
	}
	
	public static void execAppconfigForEnableAspectAutoProxy()
	{
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigForEnableAspectJAutoProxy.class);
		
		Myservice myservice = (Myservice) context.getBean("myService");
		AnotherService anotherService = (AnotherService) context.getBean("anotherService");
		
		myservice.myMethod();
		myservice.anotherMEthod("test");
		anotherService.differentMethod(3);
		
		try 
		{
			myservice.methodWithException();
			
		} catch (Exception e)
		{
			System.out.println("Exception handled in main");
		}
	}

}
