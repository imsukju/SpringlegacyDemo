package com.sj0829.UsingAutoProxy.bean;


import org.springframework.transaction.annotation.Transactional;

import com.sj0829.UsingAutoProxy.service.BusinessService;



public class BussinessObejst1 implements BusinessService{

	@Transactional // 디클레티브 aop 선언적 aop 
	@Override
	public void process() {
		System.out.println("BussinessObejst1");
	}

}
