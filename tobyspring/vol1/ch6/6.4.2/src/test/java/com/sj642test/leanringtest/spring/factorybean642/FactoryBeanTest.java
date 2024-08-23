package com.sj642test.leanringtest.spring.factorybean642;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import com.sj642.learningtest.spring.factorybean642.FactoryBeanConfig;
import com.sj642.learningtest.spring.factorybean642.Message;
import com.sj642.learningtest.spring.factorybean642.MessageFactoryBean;

import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {FactoryBeanConfig.class})
public class FactoryBeanTest {
	@Autowired
	ApplicationContext context;
	
	@Test
	public void getMessageFromFactoryBean() {
		Object message = context.getBean("message");
		assertEquals(message.getClass(), Message.class);
		assertEquals(((Message)message).getText(), "Factory Bean");
	}
	
	@Test
	public void getFactoryBean() throws Exception {
		Object factory = context.getBean("&message");
		assertEquals(factory.getClass(), MessageFactoryBean.class);
	}
}