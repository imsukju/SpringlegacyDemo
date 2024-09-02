package com.AspectjDemo.One.DeclaringAdvice.aop;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspectDeclaringAdvice {
	@Before("execution(* com.AspectjDemo.One.DeclaringAdvice.service.*.*(..))")
	public void logBeforeMethod(JoinPoint jp)
	{
		System.out.println("logBforeMethod");
		System.out.println("Class" + jp.getClass());
		System.out.println("Method" + jp.getClass().getMethods());
	}

	@AfterReturning(
			pointcut = "execution(* com.AspectjDemo.One.DeclaringAdvice.service.*.*(..))",
			returning = "result"
			)
	public void logAfterReturnig(JoinPoint jp, Object result)
	{
		System.out.println("logAfterMethod");
		System.out.println("Class" + jp.getClass());
		System.out.println("Method" + jp.getClass().getMethods());
	}
	
	@AfterThrowing(
			pointcut = ""
			,throwing = "error"
			)
	public void logAfterTrowing(JoinPoint jp, Object result)
	{
		System.out.println("logAfterMethod");
		System.out.println("Class" + jp.getClass());
		System.out.println("Method" + jp.getClass().getMethods());
	}
}
