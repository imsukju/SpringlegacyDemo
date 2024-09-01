package com.AspectjDemo.One.DeclaringAdvice.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class CommonPointcutsDelaringAdvice {
	
	@Pointcut("within(com.AspectjDemo.One.DeclaringAdvice.service..*)")
	public void inDataAccessLayer()
	{
		
	}
}
