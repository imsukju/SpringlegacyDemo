package com.sj.OperationsonPointcuts1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import com.sj.OperationsonPointcuts1.config.Appconfig;
import com.sj.OperationsonPointcuts1.service.SimpleService;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(Appconfig.class);
		
		SimpleService service = context.getBean(SimpleService.class);
		
		service.methodA();
		service.methodB();
	}

}
