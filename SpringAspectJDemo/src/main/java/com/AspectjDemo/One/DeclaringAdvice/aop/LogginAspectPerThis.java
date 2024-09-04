package com.AspectjDemo.One.DeclaringAdvice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

// 디폴트는 싱글턴이고 perthis 를 입력하면 싱글턴이 아니다

@Aspect("perthis(serviceMethods())")
//@Aspect("perthis(execution(* com.AspectjDemo.One.DeclaringAdvice.service.SampleserviceDeclaringAdvice.*(..)))")
@Component
@Scope("prototype")
public class LogginAspectPerThis {
	private int executionCout = 0;
	
	@Pointcut("execution(* com.AspectjDemo.One.DeclaringAdvice.service.SampleserviceDeclaringAdvice.*(..))")
	public void serviceMethods() {}
	
	@Before("serviceMethods()")
	public void logExecution()
	{
		this.executionCout ++;
		System.out.println("**************************************************************************");
		System.out.println("Method executed : " + 
				"execution count" + "times for this instance" +
				+ this.executionCout);
		System.out.println("**************************************************************************");
	}
}
