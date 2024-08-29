package com.AspectjDemo.One.declareingpointcut.aspect;

import java.util.Arrays;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.AspectjDemo.One.declareingpointcut.annotation.Loggable;

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
	
	@Pointcut("target(com.AspectjDemo.One.declareingpointcut.service.SpecialService)")
	private void targettransfersrvice() {}
	
	@Pointcut("args(String, ..)") // 첫번쨰 파라미터가 string인 모든메서드에게
	private void methodWithStringArg() {};
	
	// @annotation() 디지그네이터를 사용하여 특정 어노테이션이 있는 경우 매칭
	//@Loggable 은 메서드에 붙어있음
	@Pointcut("@annotation(com.AspectjDemo.One.declareingpointcut.annotation.Loggable)")
	private void withannotaion() {}
	
	// @annotation() 디지그네이터를 사용하여 클래스에 특정 어노테이션이 있는 경우 매칭
	@Pointcut("@within(com.AspectjDemo.One.declareingpointcut.annotation.SpecialComponent)")
	private void withannotaion2() {}
	
	@Pointcut("@target(com.AspectjDemo.One.declareingpointcut.annotation.Validated)")
	private void withannotation3(){}
	
	// @args 디지그네이터를 사용하여 메서드의 아규먼트가[파라미터] 특정 어노테이션을 가진 타입인 경우에 매칭
	// 아규먼트와 파라미터를 구분하지 않고 통합함
	@Pointcut("@args(com.AspectjDemo.One.declareingpointcut.annotation.Validated)")
	private void withannotation4(){}
	
	
	
	@Before("alliaseone()") // 알리아스가 정의한 조건을 스트링으로
	public void logBeforeTransfer()
	{
		System.out.println("====================This is use @Pointcut(\"execution(* transfer(..))\")===============================");
		System.out.println("TEST logBeforeTransfer pointcut");
	}
	
	
	
	@Before("withinTransferService()")
	public void logBeforewithinTransferService(JoinPoint jp)
	{
		System.out.println("====================This is use	@Pointcut(\"within(com.AspectjDemo.One.declareingpointcut.service.TransferService)\")===============================");
		System.out.println("Proxy logBeforewithinTransferService: ");
		Object proxy = jp.getThis();
		System.out.println("Proxy class: " + proxy.getClass().getName());
		
		System.out.println("Method : " + jp.getSignature().getName());
		System.out.println("Target object: " + jp.getTarget());
		System.out.println("Proxy class: " + proxy.getClass().getName());
		
	}
	
	@Before("thistransfersrvice()")
	public void logwhenproxyistransferservice(JoinPoint jp)
	{
		System.out.println("\n====================This is use @Pointcut(\"this(com.AspectjDemo.One.declareingpointcut.service.TransferService)\")===============================");
		Object proxy = jp.getThis();
		System.out.println("Logging when proxy is of type Transferservice: ");
		System.out.println("this Proxy class: " + proxy.getClass().getName());
		
	}
	
	@Before("targettransfersrvice()")
	public void logwhenPointCutTarget(JoinPoint jp)
	{
		System.out.println("\n====================This is use @Pointcut(\"target(com.AspectjDemo.One.declareingpointcut.service.SpecialService)\")===============================");
		Object proxy = jp.getThis();
		System.out.println("Logging when proxy is of type Transferservice with point cut using target: ");
		System.out.println("target Proxy class: " + jp.getTarget().getClass().getName());
		System.out.println("Method being called : " + jp.getSignature().getName());
		
	}
	
	@Before("methodWithStringArg()")
	public void logWhenPointcutArg(JoinPoint jp)
	{
		System.out.println("\n====================This is use @Pointcut(\"args(String, ..)\")===============================");
		System.out.println("Logging when proxy is of type Transferservice with point cut using arg: ");
		Object proxy = jp.getThis();
		System.out.println("Method called: " + jp.getSignature().getName());
		System.out.println("Arguments: " + Arrays.toString(jp.getArgs()));
		
	}
	
	@Before("withannotaion()")
	public void logWhenPointcutAnnotation(JoinPoint jp)
	{
		System.out.println("\n====================This is use @Pointcut(\"@annotation(com.AspectjDemo.One.declareingpointcut.annotation.Loggable)\")===============================");
		Object proxy = jp.getThis();
		System.out.println("target Proxy class: " + jp.getTarget().getClass().getName());
		System.out.println("Method called: " + jp.getSignature().getName());
		
	
	//@SpecialComponent 어노테이션이 붙은 클래스 내의 메소드가 호출될 때,
	// 메서드 이름과 클래스 이름을 로그로 출력
	}
	@Before("withannotaion2()")
	public void logWhenPointcutAnnotationWITHIN(JoinPoint jp)
	{
		System.out.println("\n====================This is use @Pointcut(\"@within(com.AspectjDemo.One.declareingpointcut.annotation.SpecialComponent)\")===============================");
		Object proxy = jp.getThis();
		System.out.println("target Proxy class: " + jp.getTarget().getClass().getName());
		System.out.println("Method" + jp.getSignature().getName());
		System.out.println("class: " + proxy.getClass().getName());
	}
	
	@Before("withannotaion3()")
	public void logWhenPointcutAnnotationTarget(JoinPoint jp)
	{
		System.out.println("\n====================This is use @Pointcut(\"@target(com.AspectjDemo.One.declareingpointcut.annotation.Validated)\")===============================");
		Object proxy = jp.getThis();
		System.out.println("target Proxy class: " + jp.getTarget().getClass().getName());
		System.out.println("Method" + jp.getSignature().getName());
		System.out.println("class: " + proxy.getClass().getName());
	}
	
	@Before("withannotaion4()")
	public void logWhenPointcutAnnotationArgs(JoinPoint jp)
	{
		System.out.println("\n====================This is use @Pointcut(\"@args(com.AspectjDemo.One.declareingpointcut.annotation.Validated)\")===============================");
		Object proxy = jp.getThis();
		System.out.println("Method" + jp.getSignature().getName());
		System.out.println("Args" + Arrays.toString(jp.getArgs()));
	}
	
	
}
	
