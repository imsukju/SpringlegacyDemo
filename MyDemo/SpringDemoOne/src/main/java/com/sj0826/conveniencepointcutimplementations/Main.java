package com.sj0826.conveniencepointcutimplementations;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj.pointcut1.service.Myservice;
import com.sj0826.conveniencepointcutimplementations.config.AppconfigConvenience;
import com.sj0826.conveniencepointcutimplementations.service.MyServiceConveniencePointcut;
import com.sj0826.conveniencepointcutimplementations.service.MySpecialService;

//스프링 AOP의 포인트컷 익스프레이션 vs aspe
public class Main {

	public static void main(String[] args) 
	{
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppconfigConvenience.class);
		
		MyServiceConveniencePointcut mservice = (MyServiceConveniencePointcut) context.getBean("myService");
		
		MySpecialService mspecialservice = (MySpecialService) context.getBean("mySpercial");
		mservice.performOperation();
		mspecialservice.performSpecialOp();
		context.close();

	}
	
}
