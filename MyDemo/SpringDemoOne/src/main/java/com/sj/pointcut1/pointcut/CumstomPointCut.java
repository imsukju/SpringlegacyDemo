package com.sj.pointcut1.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.ClassFilter;
import org.springframework.aop.MethodMatcher;
import org.springframework.aop.Pointcut;

import com.sj.pointcut1.service.CustomAnnotaion1;

public class CumstomPointCut implements Pointcut {
	int ab = 0;
	//ClassFilter 클래스 용도를 제한할?
	@Override
	public ClassFilter getClassFilter() {
		// TODO Auto-generated method stub
		//unqualified name
		//Qualified name
		return clazz -> clazz.getName().startsWith("com.sj.pointcut1.service");
	}

	@Override
	public MethodMatcher getMethodMatcher() {
		
		// TODO Auto-generated method stub
		return new MethodMatcher()
		{

			@Override
			public boolean matches(Method method, Class<?> targetClass)
			{
				// TODO Auto-generated method stub
				return method.isAnnotationPresent(CustomAnnotaion1.class) || "differentMethod".equals(method.getName());
			}

			@Override
			public boolean isRuntime() 
			{
				System.out.println("called isRuntime 메서도 :" + ab);
				// TODO Auto-generated method stub
				ab ++;
				return true;
			}

			@Override
			public boolean matches(Method method, Class<?> targetClass, Object... args) 
			{
				// TODO Auto-generated method stub
				if(args.length > 0 && args[0] instanceof String)
				{
					System.out.println("called matche[" + method.getName() + "]:arg is Stirng");
					return true;
				}
				return false;
			}
			
		};
	}

}
