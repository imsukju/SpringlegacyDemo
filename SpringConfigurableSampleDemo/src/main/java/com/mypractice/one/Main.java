package com.mypractice.one;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.mypractice.one.config.MyPracticeConfigOne;
import com.mypractice.one.service.MyPracticeService;
import com.mypractice.one.service.MyPracticeServiceImpl;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(MyPracticeConfigOne.class);
		MyPracticeService myservice = new MyPracticeServiceImpl();


		myservice.pratice();
		
	}

}
