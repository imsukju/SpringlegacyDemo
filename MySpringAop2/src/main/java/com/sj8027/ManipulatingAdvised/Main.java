package com.sj8027.ManipulatingAdvised;


import org.springframework.aop.framework.Advised;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sj8027.ManipulatingAdvised.config.MpadvisedAppConfig;
import com.sj8027.ManipulatingAdvised.service.Myservice0827Madvised;

import static java.lang.System.out;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(MpadvisedAppConfig.class);
		Myservice0827Madvised  myserviceproxy = (Myservice0827Madvised) context.getBean("myServiceProxy");
		Advised advised = (Advised) myserviceproxy;

		out.println("Advisor count: " + advised.getAdvisorCount());
		
		
		DefaultPointcutAdvisor myAdvisr = (DefaultPointcutAdvisor)context.getBean("myAdvisor");
		advised.addAdvisor(myAdvisr);
		out.println("Advisor count: " + advised.getAdvisorCount());
		
		DefaultPointcutAdvisor anotheradvisor = (DefaultPointcutAdvisor)context.getBean("anotherAdvisor");
		advised.addAdvisor(anotheradvisor);
		out.println("Advisor count: " + advised.getAdvisorCount());		
		
		advised.replaceAdvisor(anotheradvisor, myAdvisr);
		out.println("Advisor count: " + advised.getAdvisorCount());		
		
		advised.removeAdvisor(myAdvisr);
		out.println("Advisor count: " + advised.getAdvisorCount());	
		
		myserviceproxy.perform();
		
	}

}
