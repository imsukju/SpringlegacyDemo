//package com.sj.spring164.sandbox;
//
//import com.sj.spring164.dao.DaoFactory;
//import com.sj.spring164.dao.UserDao;
//
//public class SingletonTest {
//	public static void main(String[] args) {
//		ApplicationContext context = new AnnotationConfigApplicationContext(DaoFactory.class);
//		
//		System.out.println(context.getBean(UserDao.class));
//		System.out.println(context.getBean(UserDao.class));
//	}
//} 