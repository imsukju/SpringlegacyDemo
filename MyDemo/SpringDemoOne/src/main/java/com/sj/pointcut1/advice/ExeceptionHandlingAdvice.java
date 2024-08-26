package com.sj.pointcut1.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ExeceptionHandlingAdvice implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		try{
			return invocation.proceed();
		}catch(Exception e) {
			System.out.println("Exception caught int method: " + invocation.getMethod().getName()
					+ ", exception: "+ e.getMessage());
			throw e;
		}

	}

}
