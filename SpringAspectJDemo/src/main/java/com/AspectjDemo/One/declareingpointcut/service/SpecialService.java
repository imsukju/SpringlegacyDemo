package com.AspectjDemo.One.declareingpointcut.service;

public class SpecialService {

	public void specialOperation(String operation) {
		System.out.println("specail operation" + operation);
		
	}
	
	public void another(String param) {
		System.out.println("another special operation" + param);
	}
}
