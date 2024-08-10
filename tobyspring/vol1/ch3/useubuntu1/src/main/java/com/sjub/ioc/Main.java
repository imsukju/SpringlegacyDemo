package com.sjub.ioc;



import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AnnotationConfigApplicationContext context =new AnnotationConfigApplicationContext(Appconfig.class);
		
		ConfigurableListableBeanFactory customfactory = context.getBeanFactory();
		
		
		CustomObjectFactoryBean customobject1 = context.getBean("&coustumobjectbean", CustomObjectFactoryBean.class);
		
		
		
	}

}
