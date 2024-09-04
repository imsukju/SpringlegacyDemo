package com.mypractice.one.aop;

import java.util.concurrent.atomic.AtomicReferenceArray;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.DeclareParents;
import org.aspectj.lang.annotation.Pointcut;

import com.mypractice.one.model.DefaultImpl;
import com.mypractice.one.model.PracticeDeclareTracked;


@Aspect
public class ExecutionAtomicThreadPractice {
	AtomicReferenceArray<?> atomicArray = new AtomicReferenceArray<>(5);
	

	@DeclareParents(value = "com.mypractice.one.service.*+", defaultImpl = DefaultImpl.class)
	public static PracticeDeclareTracked practicTrack;
	
	@Pointcut("execution(* com.mypractice.one.service.*.*(..))")
	public void atmoicpointcut() {}
	
    @Around("atmoicpointcut()")
    public Object ymca(ProceedingJoinPoint pjp) throws Throwable {
        // 현재 객체가 PracticeDeclareTracked 타입인지 확인
        PracticeDeclareTracked trackable = (PracticeDeclareTracked) pjp.getThis();
        System.out.println("Class : " + pjp.getClass() + "\n Method :" + pjp  + "\nthis:" + pjp.getThis() + "\npjp" + pjp);
        for(Object obj : pjp.getArgs())
        {
            System.out.println("Argument : " + obj);
        }
        
        Class<?> clazs = pjp.getThis().getClass();
        Class<?> superclazs = clazs.getSuperclass();
        Class<?>[] inter = clazs.getInterfaces();
        System.out.println("Implementing interface :");
        for(Class<?> a :  inter)
        {
        	System.out.println(a+", ");
        }
        
        // trackable을 사용해 메서드 호출
        if (trackable != null) {
            trackable.hello("hi");
        }
        // 메서드 인자 수정
        Object[] myarg = pjp.getArgs();
        if (myarg != null && myarg.length > 0) {  // 인자가 1개 이상일 때만 수정
            myarg[0] = "hi";  // 첫 번째 인자를 "hi"로 변경
        }

        
        // 수정된 인자를 사용해 메서드 실행
        Object ret = pjp.proceed(myarg);

        System.out.println("Around advice executed");

        return ret;
    }
}
