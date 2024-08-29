package com.AspectjDemo.One.declareingpointcut.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;


//StereoType: 사전적인 의미는 정형화 지만
//Spring에서 @Componet를 "스테레오타입(stereotype)" 어노테이션이라고 정의하는 이유는
//이 애너테이션이 클래의 역활과 목적을 나타내는 메타데이터를 제공
//스테레오타입 어노테이션은 개발자가 특정 클래스가 애플리케이션 내에서 어떤 역활을 수행하는지 명확하게 표현하도록 돕습니다.
@Service
public class TransferService {
	public void transfer(String account, double amount) {
		System.out.println("Transfering" + amount + "to account" + account);
		
	}
	
	public void checkBalance()
	{
		System.out.println("check balance");
	}
}
