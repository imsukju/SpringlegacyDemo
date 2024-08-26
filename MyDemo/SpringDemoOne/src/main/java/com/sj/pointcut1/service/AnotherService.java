package com.sj.pointcut1.service;

import static java.lang.System.out;

public class AnotherService {
	public void myMethod() 
	{
		System.out.println("AAAAA");
	}
	
	public void differentMethod(int number)
	{
		out.println("this method :" + number);
	}

}
