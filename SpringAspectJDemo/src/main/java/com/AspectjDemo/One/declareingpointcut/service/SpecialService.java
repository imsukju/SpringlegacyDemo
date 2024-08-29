package com.AspectjDemo.One.declareingpointcut.service;

import com.AspectjDemo.One.declareingpointcut.annotation.Loggable;
import com.AspectjDemo.One.declareingpointcut.annotation.SpecialComponent;
import com.AspectjDemo.One.declareingpointcut.annotation.Validated;

@SpecialComponent
public class SpecialService {

	@Loggable
	public void specialOperation(String operation) {
		System.out.println("specail operation" + operation);
		
	}
	
	public void another(@Validated String param) {
		System.out.println("another special operation" + param);
	}
}
