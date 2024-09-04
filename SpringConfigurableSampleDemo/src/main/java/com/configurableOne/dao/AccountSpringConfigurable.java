package com.configurableOne.dao;

import org.springframework.beans.factory.annotation.Autowire;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Repository;

import com.configurableOne.service.MyServiceSpringConfigurable;


@Configurable(autowire = Autowire.BY_TYPE, dependencyCheck = true
, preConstruction = true)
public class AccountSpringConfigurable {
	@Autowired
	private MyServiceSpringConfigurable myservice;
	
	public void dosometing()
	{
		if(myservice != null)
		{
			myservice.performService();
		}
		else
		{
			System.out.println("Account : doSomething:MyService is not injected!");
		}
	}
}
