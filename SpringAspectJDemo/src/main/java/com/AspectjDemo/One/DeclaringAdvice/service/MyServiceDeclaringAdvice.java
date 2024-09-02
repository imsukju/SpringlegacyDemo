package com.AspectjDemo.One.DeclaringAdvice.service;

import org.springframework.stereotype.Service;

import com.AspectjDemo.One.DeclaringAdvice.annotation.Auditable;

@Service
public class MyServiceDeclaringAdvice {

	
	public void performTask()
	{
		System.out.println("MyServiceDeclaringAdvice:: performTask");
		try
		{
			Thread.sleep(2000); // performTask() 호출하게한 콜스택의 주인이 스레드가 지금부터 1초동안 사용하지 않겠다
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void helloWorld(@Auditable(value = "hello") String hello)
	{
		
	}
}















//interface Hello
//{
//	void hellomethod();
//}
//
//class testclass
//{
//	void myhellomethod(Hello h)
//	{
//		h.hellomethod();
//	}
//	
//	void printFuntionName(String hi)
//	{
//		System.out.println("hello name :" + hi);
//	}
//	
//	void abc()
//	{
//		
//		myhellomethod(Hello::printFuntionName);
//	}
//}