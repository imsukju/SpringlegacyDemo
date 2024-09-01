package com.AspectjDemo.One.DeclaringAdvice.aop;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspectDeclaringAdvice {
	@Before("execution(* com.AspectjDemo.One.DeclaringAdvice.service.*.*(..))")
	public void logBeforeMethod(Joinpoint jp)
	{
		System.out.println("logBforeMethod");
		System.out.println("Class" + jp.getClass());
		System.out.println("Method" + jp.getClass().getMethods());
	}

	@AfterReturning(
			pointcut = "execution(* com.AspectjDemo.One.DeclaringAdvice.service.*.*(..))",
			returning = "result"
			)
	public void logAfterReturnig(Joinpoint jp, Object result)
	{
		System.out.println("logAfterMethod");
		System.out.println("Class" + jp.getClass());
		System.out.println("Method" + jp.getClass().getMethods());
	}
	
	@AfterThrowing(
			pointcut = ""
			,throwing = "error"
			)
	public void logAfterTrowing(Joinpoint jp, Object result)
	{
		System.out.println("logAfterMethod");
		System.out.println("Class" + jp.getClass());
		System.out.println("Method" + jp.getClass().getMethods());
	}
}
