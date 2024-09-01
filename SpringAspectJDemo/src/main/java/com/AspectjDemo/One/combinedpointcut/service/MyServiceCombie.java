package com.AspectjDemo.One.combinedpointcut.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class MyServiceCombie {
	public void purfromBusinessLogic()
	{
		System.out.println("MyServiceCombie purfromBusinessLogic");
	}
}
