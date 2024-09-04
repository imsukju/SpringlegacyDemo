package com.mypractice.one.service;

public class MyPracticeServiceImpl implements MyPracticeService{

	@Override
	public void pratice() {
		System.out.println("MyPracticeServiceImpl Override Method: ");
		
	}
	
	public void orginalMethod(String hello)
	{
		System.out.println("This is MyPracticeServiceImpl Original Method :");
	}
	
	
}
