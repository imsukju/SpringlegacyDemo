package com.AspectjDemo.One.DeclaringAdvice.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.ParameterNameDiscoverer;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.stereotype.Component;

import com.AspectjDemo.One.DeclaringAdvice.annotation.AuditCode;
import com.AspectjDemo.One.DeclaringAdvice.annotation.Auditable;
import com.AspectjDemo.One.DeclaringAdvice.annotation.AuditableCode;
import com.AspectjDemo.One.DeclaringAdvice.service.model.MyType;

import static java.lang.System.out;

import java.lang.reflect.Method;
import java.util.Collection;
@Aspect
@Component
public class SampleAspectDeclaring {
	
	@Before("execution(* com.AspectjDemo.One.DeclaringAdvice.service.Sample+.sampleGenericMethod(*)) && " + " args(param) ")
	public void beforeSampleMethod(JoinPoint jp, MyType param)
	{
		out.println("beforeSampleMethod: Before sampleGeneric with MyType param: " + param);
	}
	
	@Before("execution(* com.AspectjDemo.One.DeclaringAdvice.service.Sample+.sampleGenericCollectionMethod(*)) && " + " args(param) ")
	public void beforeSampleCollectionMethod(JoinPoint jp, Collection<?> param)
	{
		out.println("beforeSampleMethod: Before sampleGeneric with MyType param: " + param);
		if(!param.isEmpty())
		{
			Object firstElement = param.iterator().next();
			if (firstElement instanceof MyType)
			{
				out.println("first element in collecction is of type Type : " + firstElement);
			}
		}
	}
	
	
	@Before("execution(* com.AspectjDemo.One.DeclaringAdvice.service.Sample+.sampleGenericCollectionMethod(*)) && " + " args(param) ")
	public void beforeSampleCollectionMethodForDetermingArgumentName(JoinPoint jp, Collection<?> param)
	{

		out.println("--beforeSampleMethodFordetermingArgumentName--");
		MethodSignature ms =(MethodSignature) jp.getSignature();
		Method method = ms.getMethod();	
		ParameterNameDiscoverer pnd = new StandardReflectionParameterNameDiscoverer();
		String[] parameterNames = pnd.getParameterNames(method);
		
		if(parameterNames != null)
		{
			for (String paraName : parameterNames)
			{
				out.println("Determined parameter name: " + paraName);
			}
		}
		else
		{
			out.println("no parameter names found");
		}

		out.println("++beforeSampleMethodFordetermingArgumentName++");
	}
	
	// 디버그 정보 없이 컴파일하거나 런타임 시에 포인트컷을 해석할 때,
	// 어드바이스 선언에 사용된 인자의 이름은 사용할 수 없습니다.
	// 이러한 상황에서만 @Before 어노테이션에 인자 이름[argNames]을 제공해야 하며,
	// 이 이름들은 어노테이션이 달린 메서드[audit]에서 사용된 이름과 동일해야 합니다.
	// 형식은 단순히 쉼표로 구분된 리스트입니다.
	@Before
	(
		value ="execution(* com.AspectjDemo.One.DeclaringAdvice.service.SampleserviceDeclaringAdvice.*(..))" 
		+ " && target(bean) && @annotation(auditableCode)",
		argNames = "bean,auditableCode"
	)
	public void audit(Object bean, AuditableCode auditableCode)
	{
		AuditCode code = auditableCode.value();
		out.println("Audit - bean: "+ bean.getClass().getName() + ", Code : " +code);
	}
	
	@Before
	(
		value ="execution(* com.AspectjDemo.One.DeclaringAdvice.service.SampleserviceDeclaringAdvice.*(..))" 
		+ " && target(bean) && @annotation(auditablecode)",
		argNames = "jp,bean,auditablecode"
	)
	public void audit(JoinPoint jp, Object bean, AuditableCode auditablecode)
	{
		AuditCode code = auditablecode.value();
		out.println("Methods :" + jp.getSignature().getName());
		out.println("Audit - bean: "+ bean.getClass().getName() + ", Code : " +code);
	}
}
