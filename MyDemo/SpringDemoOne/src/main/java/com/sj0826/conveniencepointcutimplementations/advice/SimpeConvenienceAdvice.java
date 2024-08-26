package com.sj0826.conveniencepointcutimplementations.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class SimpeConvenienceAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("Before method" + invocation.getMethod().getName());
		Object result = invocation.proceed();
		System.out.println("After method" + invocation.getMethod().getName());
		return result;
	}

}
