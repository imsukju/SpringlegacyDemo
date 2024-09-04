package com.configurableOne.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
// 이 클래스가 런타임때 바이트코드로 추가가된다

@Aspect
public class ProfilingAspect {

    @Around("methodsToBeProfiled()")
    public Object profile(ProceedingJoinPoint pjp) throws Throwable {
        StopWatch sw = new StopWatch(getClass().getSimpleName());
        try {
        	System.out.println("+ProfilingAspect::profile"+pjp.getTarget().getClass().getName()
        			+
        			"\n signature name" + pjp.getSignature().getName()
        			);
        	
            sw.start(pjp.getSignature().getName());
            return pjp.proceed();
        } finally {
            sw.stop();
            System.out.println(sw.prettyPrint());
        }
        
    }

    @Pointcut("execution(public * com.configurableOne..*.*(..))")
    public void methodsToBeProfiled(){}
}