package com.AspectjDemo.One.combinedpointcut.dao;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import static java.lang.System.out;

@Repository
public class MydaoCombine {

	public void helloprint() {
		System.out.println("hello");
	}
}
