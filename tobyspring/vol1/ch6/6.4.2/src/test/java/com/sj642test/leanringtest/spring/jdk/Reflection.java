package com.sj642test.leanringtest.spring.jdk;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.lang.reflect.Method;
import java.util.Date;
public class Reflection {
	@Test
	public void invokeMethod() throws Exception {
		String name = "Spring";

		// length
		assertEquals(name.length(), 6);
		
		Method lengthMethod = String.class.getMethod("length");
		assertEquals((Integer)lengthMethod.invoke(name), 6);
		
		// toUpperCase
		assertEquals(name.charAt(0), 'S');
		
		Method charAtMethod = String.class.getMethod("charAt", int.class);
		assertEquals((Character)charAtMethod.invoke(name, 0), 'S');
	}
	
	@Test 
	public void createObject() throws Exception {
		Date now = java.util.Date.class.getDeclaredConstructor().newInstance();
	}
}