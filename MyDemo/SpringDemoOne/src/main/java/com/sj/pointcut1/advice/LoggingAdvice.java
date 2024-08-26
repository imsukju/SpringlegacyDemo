package com.sj.pointcut1.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import static java.lang.System.out;
public class LoggingAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		
		System.out.println("Before method : " + invocation.getMethod().getName());
		Object result = invocation.proceed();
		System.out.println("After method : " + invocation.getMethod().getName());
		return null;
	}

}
