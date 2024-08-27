package com.sj8027.ManipulatingAdvised.advice;

import static java.lang.System.out;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

public class DenuginterceptorMpAdvised implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		out.println("AnotherInerceptor :Before method" + invocation.getMethod());
		Object retVal = invocation.proceed();
		out.println("AnotherInerceptor :After method" + invocation.getMethod());
		out.println("================================================================================");
		return null;
	}

}
