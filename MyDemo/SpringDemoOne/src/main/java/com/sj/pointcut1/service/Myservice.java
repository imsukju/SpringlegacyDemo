package com.sj.pointcut1.service;
import static java.lang.System.out;
public class Myservice {
	
	@CustomAnnotaion1
	public void myMethod()
	{
		out.println("Executing myMethod");
		
		
	}
	
	@CustomAnnotaion1
	public void anotherMEthod(String arg)
	{
		out.println("Executing anotherMethod with arg: " + arg);
	}
	
	public void methodWithException() throws Exception 
	{
		throw new Exception("Exception in methodWithException");
	}
	
}
