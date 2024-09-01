package com.AspectjDemo.One.DeclaringAdvice.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import static java.lang.System.out;
@Aspect
@Component
public class AccountAspectDeclaringAdvice 
{	//인라인으로 사용
	@Around("execution(java.util.List<com.AspectjDemo.One.DeclaringAdvice.service.model.Account> " 
			+ "com.AspectjDemo.One.DeclaringAdvice.service.AccountService.find*(..)) "
			// 여기는 참조를 사용?
			+"&& com.AspectjDemo.One.DeclaringAdvice.aspect.CommonPointcutsDelaringAdvice.inDataAccessLayer()"
			+ " && " + "args(accountholder)")
	public Object perProceesingQuerreyPattern(ProceedingJoinPoint pj,
			String accountholder) throws Throwable 
	{
		out.println("Class : " + pj.getTarget().getClass().getName());
		out.println("Method : " + pj.getSignature().getName());
		
//		Object ret = pj.proceed();
		String newPaatern = this.perProcess(accountholder);
		
		return pj.proceed(new Object[] {newPaatern});
	}
	
	private String perProcess(String pattern)
	{
		return pattern.toUpperCase();
	}
}
