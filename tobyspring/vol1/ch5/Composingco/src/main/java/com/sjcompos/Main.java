package com.sjcompos;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.ApplicationContext;
public class Main {
	public static void main(String[] args) {
		ApplicationContext ctx = new AnnotationConfigApplicationContext(ConfigB.class);

		// now both beans A and B will be available...
		A a = ctx.getBean("a",A.class);
		B b = ctx.getBean("b",B.class);
		
		System.out.println(a);
		System.out.println(b);
	}
}
