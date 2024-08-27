package com.sj8027.ManipulatingAdvised.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.support.StaticMethodMatcher;
import org.springframework.aop.support.StaticMethodMatcherPointcut;



public class MpAdvisedSpecialPointcut extends StaticMethodMatcherPointcut {
	
	@Override
	public boolean matches(Method method, Class<?> targetClass) {
		// TODO Auto-generated method stub
		return "perform".equals(method.getName());
	}

}
