package com.sj0826.conveniencepointcutimplementations.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.interceptor.TransactionProxyFactoryBean;

import com.sj0826.conveniencepointcutimplementations.service.MyServiceImpl;
import com.sj0826.conveniencepointcutimplementations.service.MySpecialService;
import com.sj0826.conveniencepointcutimplementations.service.MySpecialServiceImpl;
import com.sj0826.conveniencepointcutimplementations.transaction.SimpleTransationManager;

@Configuration
@EnableTransactionManagement
public class AppconfigConvenience 
{
	
	@Bean
	public PlatformTransactionManager transactionManager()
	{
		return new SimpleTransationManager();
	}
	
	private TransactionProxyFactoryBean creatProxy
	(PlatformTransactionManager transactionManager,
			Object target,
			Properties transactionAttributes
	)
	{
		TransactionProxyFactoryBean proxyFacotyBean = new TransactionProxyFactoryBean();
		
		
		proxyFacotyBean.setTarget(target);
		proxyFacotyBean.setTransactionManager(transactionManager);
		proxyFacotyBean.setTransactionAttributes(transactionAttributes);
		proxyFacotyBean.setProxyTargetClass(true);
		
		return proxyFacotyBean;
		
	}
	
	@Bean
	public TransactionProxyFactoryBean myService (PlatformTransactionManager transationManager)
	{
		Properties	properties = new Properties();
		properties.setProperty("*","PROPAGATION_REQUIRED");
		return creatProxy(transationManager, new MyServiceImpl(), properties);
	}
	
	@Bean
	public TransactionProxyFactoryBean mySpercial (PlatformTransactionManager transationManager)
	{
		Properties	properties = new Properties();
		properties.setProperty("get","PROPAGATION_REQUIRED,readOnly");
		properties.setProperty("find","PROPAGATION_REQUIRED,readOnly");
		properties.setProperty("load","PROPAGATION_REQUIRED,readOnly");
		properties.setProperty("store","PROPAGATION_REQUIRED,readOnly");
		return creatProxy(transationManager, new MySpecialServiceImpl(), properties);
	}
	
}
