package com.AspectjDemo.One.DeclaringAdvice.aop;

import java.util.concurrent.atomic.AtomicInteger;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionCountingAspectdeclaring {

	// Atomic : 원자성... Thread가 원자성을 지킬 수 있도록 해준다.
	//int a[전역변수]  = 0; 
	// a = 10; a에 10이라는 값을 할려할려고 하는순간에, OS가 Thread에게 할당한 time slice(cpu 사용시간)가 경과하면,,,
	//sleep(0)
	// 사용시간을 뺏긴다면?
	private AtomicInteger executionCount = new AtomicInteger(0);
	
	@Around("execution(* com.AspectjDemo.One.DeclaringAdvice.service.MyServiceDeclaringAdvice.*(..))")
	public Object countExecutions(ProceedingJoinPoint pjp) throws Throwable
	{
											// 대표적인 쓰레드 세이프코드?
		int currentCount = this.executionCount.incrementAndGet();
		
		System.out.println("countExecutions : before proceed executionCount: " + currentCount);
		Object ret = pjp.proceed();
		System.out.println("countExecutions : after proceed executionCount: " + this.getExecutionCount());
		this.getExecutionCount();
		return ret;
	}
	
	public int getExecutionCount() {return this.executionCount.get();}
}
