package com.sj.spring165.sandbox;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj.spring165.dao.DaoFactory;
import com.sj.spring165.dao.UserDao;

public class SingletonTest {
	public static void main(String[] args) {
		ApplicationContext context = new AnnotationConfigApplicationContext(com.sj.spring165.dao.DaoFactory.class);
		
		System.out.println(context.getBean(UserDao.class));
		System.out.println(context.getBean(UserDao.class));
	}
} 
