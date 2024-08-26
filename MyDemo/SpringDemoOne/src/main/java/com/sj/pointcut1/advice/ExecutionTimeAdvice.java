package com.sj.pointcut1.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class ExecutionTimeAdvice implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		long startTimeMills = System.currentTimeMillis();// 컴퓨터가 부팅되는 순간부터 시간을 측정
		try{
			invocation.proceed();
		} finally {
			long timeTakemills = System.currentTimeMillis() - startTimeMills;
			System.out.println("Excution time of " + invocation.getMethod().getName() + ":::" + timeTakemills + "ms");
			
		}
		
		return null;
	}

}
