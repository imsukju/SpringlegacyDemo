package com.stvalue1.idref.dao;

import java.util.Properties;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ClientBean {
	TargetBean tb ;
	
	public void setTargetName(String tb)
	{
		ApplicationContext a = new AnnotationConfigApplicationContext(AppConfig.class);
		this.tb = a.getBean(tb,TargetBean.class);
	}
}
