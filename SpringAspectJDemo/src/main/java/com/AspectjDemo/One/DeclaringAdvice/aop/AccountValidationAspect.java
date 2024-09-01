package com.AspectjDemo.One.DeclaringAdvice.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import com.AspectjDemo.One.DeclaringAdvice.service.model.Account;

@Aspect
@Component
public class AccountValidationAspect {
	@Pointcut("execution (* com.AspectjDemo.One.DeclaringAdvice.dao.*.(..) && " 
			+ "args(account,..)")
	public void accountDataAccessOperation(Account account)
	{
	}
	
	@Before("accountDataAccessOperation(account)")
	public void validateAccount(Account account)
	{
		if (account.getBalace() < 0)
		{
			throw new IllegalArgumentException("Account balacne cannot be negative");
		}
		System.out.println("Account validation passed for : ");
	}

}
