package com.AspectjDemo.One.DeclaringAdvice.service;

import java.util.Collection;

import org.springframework.stereotype.Service;

import com.AspectjDemo.One.DeclaringAdvice.annotation.AuditCode;
import com.AspectjDemo.One.DeclaringAdvice.annotation.AuditableCode;
import com.AspectjDemo.One.DeclaringAdvice.service.model.MyType;

@Service
public class SampleserviceDeclaringAdvice implements Sample<MyType>{
	
	@AuditableCode(AuditCode.USER_ACTION)
	@Override
	public void sampleMethod(String data)
	{
		System.out.println("Executings sampleMethod wtih data" + data);
	}
	
	@AuditableCode(AuditCode.USER_ACTION)
	@Override
	public void sampleGenericMethod(MyType param) {
		// TODO Auto-generated method stub
		System.out.println("Executings sampleGenericMethod wtih data" + param);
		
	}

	@Override
	public void sampleGenericCollectionMethod(Collection<MyType> param) {
		// TODO Auto-generated method stub
		System.out.println("Executings sampleGenericCollectionMethod wtih data" + param);
	}
	
	
	
}
