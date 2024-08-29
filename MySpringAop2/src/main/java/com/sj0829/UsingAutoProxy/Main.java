package com.sj0829.UsingAutoProxy;

import org.springframework.aop.framework.AopProxyUtils;
import org.springframework.cglib.proxy.Proxy;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj0829.UsingAutoProxy.bean.Mybean;
import com.sj0829.UsingAutoProxy.config.AppconfigUsingAutoProxy;
import com.sj0829.UsingAutoProxy.service.BusinessService;

import static java.lang.System.out; 

public class Main {
	public static void printProxyinfo(Object obj)
	{
		Class<?> targetClass = AopProxyUtils.ultimateTargetClass(obj);
		out.println("Bean class: " + obj.getClass().getName());
		out.println("Is JDK Dynamin proxy: " + Proxy.isProxyClass(obj.getClass()));
		out.println("IS CGLIB Proxy :"+obj.getClass().getName().contains("$$"));
		out.println("Target Class"+targetClass.getName());
		out.println("=========================================================================");
		
	}

	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(AppconfigUsingAutoProxy.class);
		
		Mybean jdkMyBean = context.getBean("myBean",Mybean.class);
		printProxyinfo(jdkMyBean);
		
		Mybean onlybean = context.getBean("onlybean",Mybean.class);
		printProxyinfo(onlybean);
		
		BusinessService buz1 = context.getBean("businessObject1",BusinessService.class);
		printProxyinfo(buz1);
		BusinessService buz2 = context.getBean("businessObject2",BusinessService.class);
		printProxyinfo(buz2);
	}

}
