package com.AspectjDemo.One.DeclaringAdvice.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import com.AspectjDemo.One.DeclaringAdvice.service.model.DefaultUsageTrackedDeclare;
import com.AspectjDemo.One.DeclaringAdvice.service.model.UsageTrackedDeclare;
@Aspect
@Component
public class UsageTrackingAspectDeclare {
	@DeclareParents(value = "com.AspectjDemo.One.DeclaringAdvice.service.*+",defaultImpl = DefaultUsageTrackedDeclare.class )
	
	public static UsageTrackedDeclare mixin;
	
	@Before("execution(* com.AspectjDemo.One.DeclaringAdvice.service.*.*(..))"
			+" && this(usageTrackedDeclare)")
	public void trackUsage(UsageTrackedDeclare usageTrackedDeclare)
	{
		usageTrackedDeclare.incrementUsage();
		System.out.println("Usage count incremented: "+ usageTrackedDeclare.getUseCount());
	}
	
}
