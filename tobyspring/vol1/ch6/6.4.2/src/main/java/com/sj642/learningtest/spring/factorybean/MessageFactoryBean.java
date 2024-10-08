package com.sj642.learningtest.spring.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class MessageFactoryBean implements FactoryBean<Message>{
	String text;
	
	public MessageFactoryBean() {
		System.out.println("MessageFactoryBean Default Constructor");
	}
	
	public void setText(String text) {
		this.text = text;
	}

	public Message getObject() throws Exception {
		return Message.newMessage(this.text);
	}

	public Class<? extends Message> getObjectType() {
		return Message.class;
	}

	public boolean isSingleton() {
		return true;
	}
}