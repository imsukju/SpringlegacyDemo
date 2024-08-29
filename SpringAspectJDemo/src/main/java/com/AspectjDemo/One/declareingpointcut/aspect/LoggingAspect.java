package com.AspectjDemo.One.declareingpointcut.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component // 이 어노테이션이 적용된 클래스는 Spring IOC에 의해 자동으로 객체를 만들어져서 빈으로 등록된다
public class LoggingAspect {
	
	//특정 메서드 이름 ex :transfer 를 매칭하는 포인트컷
	@Pointcut("execution(* transfer(..))")
	private void alliaseone() {} // 알리아스임
	
	//TransferService 내의 모든 메서드를 매칭시킨다
	@Pointcut("within(com.AspectjDemo.One.declareingpointcut.service.TransferService)")
	private void withinTransferService() {}
	
	@Pointcut("this(com.AspectjDemo.One.declareingpointcut.service.TransferService)")
	private void thistransfersrvice() {}
	
	@Before("alliaseone()") // 알리아스가 정의한 조건을 스트링으로
	public void logBeforeTransfer()
	{
		System.out.println("TEST logBeforeTransfer pointcut");
		System.out.println("===============================================================");
	}
	
	@Before("withinTransferService()")
	public void logBeforewithinTransferService(JoinPoint jp)
	{
		System.out.println("Proxy logBeforewithinTransferService: ");
		Object proxy = jp.getThis();
		System.out.println("Proxy class: " + proxy.getClass().getName());
		
		System.out.println("Method : " + jp.getSignature().getName());
		System.out.println("Target object: " + jp.getTarget());
		System.out.println("Proxy class: " + proxy.getClass().getName());
		System.out.println("===============================================================");
		
	}
	
	@Before("thistransfersrvice()")
	public void logwhenproxyistransferservice(JoinPoint jp)
	{
		Object proxy = jp.getThis();
		System.out.println("Logging when proxy is of type Transferservice: ");
		System.out.println("this Proxy class: " + proxy.getClass().getName());
		System.out.println("===============================================================");
	}
}
	
