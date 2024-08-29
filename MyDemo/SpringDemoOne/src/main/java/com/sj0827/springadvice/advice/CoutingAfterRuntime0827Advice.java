package com.sj0827.springadvice.advice;

import static java.lang.System.out;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class CoutingAfterRuntime0827Advice implements AfterReturningAdvice{
	private int count;
	
	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		count ++;
		out.println("After method: " + 
				method.getName() +
				", return value: " + 
				returnValue + ", count=" + count);
	}


	
	public int getCount() {
		return count;
	}
}
