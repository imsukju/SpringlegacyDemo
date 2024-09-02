package com.AspectjDemo.One.DeclaringAdvice.aop;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TimingAspect {
	@Around("execution(* com.AspectjDemo.One.DeclaringAdvice.service.MyServiceDeclaringAdvice.*(..))")
	public Object measureExecutionTime(ProceedingJoinPoint pjp) throws Throwable
	{
		long startTime = System.currentTimeMillis();// 1970년도 이후부터 지금까지 경과된 시간을 반환해줌
		Date date = new Date(startTime);
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
		String formattedDate = sdf.format(date);
		System.out.println("Entrance Time " + formattedDate);
		System.out.println("Timer started for methos: " + pjp.getSignature().getName());
		
		Object ret = pjp.proceed();
		long endTime = System.currentTimeMillis();
		long duration = endTime - startTime;
		
		System.out.println("Timer stopped for method: " + pjp.getSignature().getName());
		System.out.println("Execution time " + duration + "ms");
		return ret;
	}
	
}
