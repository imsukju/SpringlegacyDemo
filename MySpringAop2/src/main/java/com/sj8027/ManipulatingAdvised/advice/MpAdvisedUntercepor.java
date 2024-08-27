package com.sj8027.ManipulatingAdvised.advice;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import static java.lang.System.out;

public class MpAdvisedUntercepor implements MethodInterceptor {

	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		// TODO Auto-generated method stub
		out.println("AnotherInerceptor :Before method" + invocation.getMethod());
		Object retVal = invocation.proceed();
		out.println("AnotherInerceptor :After method" + invocation.getMethod());
		out.println("================================================================================");
		return null;
	}

}
