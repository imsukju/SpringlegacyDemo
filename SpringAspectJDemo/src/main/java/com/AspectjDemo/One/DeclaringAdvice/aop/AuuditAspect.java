package com.AspectjDemo.One.DeclaringAdvice.aop;

import static java.lang.System.out;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.AspectjDemo.One.DeclaringAdvice.annotation.Auditable;

@Aspect
@Component
public class AuuditAspect {
	// 특정 어노테이션이 클래스 레벨에서 적용될 때, 해당 클래스의 모든 메서드에 매칭! 클래스 로드타임때
	@Pointcut("@within(com.AspectjDemo.One.DeclaringAdvice.annotation.Auditable)")
	public void AuditablePoin() {}
	
	//@target : 특정 어노테이션이 적용된 객체의 모든 메서드에 매칭...
	// 이 포인트컷은 런타임 시점에, 프록시 객체가 생성될 때
	// 해당 객체의 클래스가 @Auditable 어노테이션을 가지고 있는지 확인.
	// 만약 그러하다면 해당 객체의 모든 메서드가 포인트컷에 매칭
	@Pointcut("@target(com.AspectjDemo.One.DeclaringAdvice.annotation.Auditable)")
	public void targetauditablecalss() {}
	
	@Pointcut("@annotation(auditable)")
	public void annotationpointcut(Auditable auditable)
	{
		
	}
	
	@Pointcut("execution(* com.AspectjDemo.One.DeclaringAdvice.service.*.*(..)) &&"
			+ " @arg(com.AspectjDemo.One.DeclaringAdvice.annotation.Auditable)")
	public void methodWithAuditableArgs() {};
	
	
	
	@Before("AuditablePoin()")
	public void withinAiditable(JoinPoint jp)
	{
		out.println("With in annotation Class : " + jp.getClass());
	}
	@Before("targetauditablecalss()")
	public void logtargetauditable(JoinPoint jp)
	{
		out.println("Target Annotation Class : " + jp.getClass());

	}
	
	@Before("annotationpointcut(auditable)")
	public void logannotation(JoinPoint jp, Auditable auditable)
	{
		out.println("Target Annotation Class : " + jp.getClass());

	}
	
	@Before("methodWithAuditableArgs()")
	public void annotationarg()
	{
		out.println("annotation args");
	}
	
}
