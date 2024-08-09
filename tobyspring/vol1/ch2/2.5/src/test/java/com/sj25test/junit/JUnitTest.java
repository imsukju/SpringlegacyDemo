package com.sj25test.junit;



import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intheeast.springframe.learningtest.factorybean.MyCustomObject;
import com.intheeast.springframe.learningtest.factorybean.MyCustomObjectFactoryBean;



@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestJunit.class})

public class JUnitTest {
	@Autowired 
	ApplicationContext context;  // Spring IoC Container가 자동 주입됨.
	
	
	
	static Set<JUnitTest> testObjects = new HashSet<JUnitTest>();
	static ApplicationContext contextObject = null;
	
	@Test
	@Order(1)
	public void test1() {
		assertFalse(testObjects.contains(this));
		
		testObjects.add(this);
		
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Test
	@Order(2)
	public void test2() {
		assertFalse(testObjects.contains(this));
		testObjects.add(this);
		
		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	

	public void testdirty() throws IllegalStateException, Exception {
		ConfigurableApplicationContext configAppContext = 
				(ConfigurableApplicationContext) context;
	
		// 빈으로 등록된 FactoryBean은 getBean으로 가져올 때 '&'를 붙여야 함.
		// 그렇지 않으면, FactoryBean이 생성하는 빈 객체를 리턴함!!!
		MyCustomObjectFactoryBean factoryBean =
				(MyCustomObjectFactoryBean) context.getBean("&MyCustomObjectFactory");
		
		
		configAppContext.getBeanFactory().registerSingleton("myCustomObject2", 
				factoryBean.getObject());
		
		MyCustomObject myObject2 = 
				(MyCustomObject) context.getBean("myCustomObject2");
		Assertions.assertNotNull(myObject2);
		
		factoryBean.getObject().setName("hh");
//		AnnotationConfigApplicationContext dirty = new AnnotationConfigApplicationContext(com.intheeast.springframe.learningtest.factorybean.AppConfig.class);
//		
//		context = dirty;

	}
	
	@Test 
	@Order(4)
	@DirtiesContext
	public void test3() throws IllegalStateException, Exception {
		assertFalse(testObjects.contains(this));
		testObjects.add(this);
		testdirty();

		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Order(5)
	@Test 
	public void test4() throws IllegalStateException, Exception {
		assertFalse(testObjects.contains(this));
		testObjects.add(this);

		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	
	@Order(6)
	@Test 
	public void test5() throws IllegalStateException, Exception {
		assertFalse(testObjects.contains(this));
		testObjects.add(this);

		assertTrue(contextObject == null || contextObject == this.context);
		contextObject = this.context;
	}
	

}