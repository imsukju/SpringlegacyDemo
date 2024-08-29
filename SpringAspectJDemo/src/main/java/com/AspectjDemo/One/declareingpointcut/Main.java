package com.AspectjDemo.One.declareingpointcut;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.AspectjDemo.One.declareingpointcut.aspect.LoggingAspect;
import com.AspectjDemo.One.declareingpointcut.config.AppConfigOne;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigOne.class);
		
		AppConfigOne config = (AppConfigOne) context.getBean("appConfigOne");
		
		LoggingAspect logAspect = (LoggingAspect) context.getBean("loggingAspect");
		
	}

}
