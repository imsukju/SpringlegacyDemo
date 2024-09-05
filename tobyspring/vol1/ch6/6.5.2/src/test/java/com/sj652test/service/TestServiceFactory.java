package com.sj652test.service;

import javax.sql.DataSource;

import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.sj652.dao.UserDaoJdbc;
import com.sj652.service.DummyMailSender;
import com.sj652.service.NameMatchClassMethodPointcut;
import com.sj652.service.TransactionAdvice;
import com.sj652.service.TxProxyFactoryBean;
import com.sj652.service.UserService;
import com.sj652.service.UserServiceImpl;

@Configuration
public class TestServiceFactory {
	
	@Bean
	public DataSource dataSource() {
		
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
		
		dataSource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost:3306/sbdt_db?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");

		return dataSource;
	}
	
	
	@Bean
	public DataSourceTransactionManager transactionManager() {
		DataSourceTransactionManager dataSourceTransactionManager = new DataSourceTransactionManager();
		dataSourceTransactionManager.setDataSource(dataSource());
		return dataSourceTransactionManager;
	}
	
	// aop
	//<bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator" />
	@Bean
    public DefaultAdvisorAutoProxyCreator advisorAutoProxyCreator() {
		return new DefaultAdvisorAutoProxyCreator();
	}
	
	@Bean
	public TransactionAdvice transactionAdvice() {
		TransactionAdvice transactionAdvice = new TransactionAdvice();
		transactionAdvice.setTransactionManager(transactionManager());
		return transactionAdvice;
	}

	@Bean
	public NameMatchClassMethodPointcut transactionPointcut() {
		NameMatchClassMethodPointcut nameMatchClassMethodPointcut = new NameMatchClassMethodPointcut();
		nameMatchClassMethodPointcut.setMappedClassName("*ServiceImpl");
		nameMatchClassMethodPointcut.setMappedName("upgrade*");
		return nameMatchClassMethodPointcut;
	}

	@Bean
	public DefaultPointcutAdvisor transactionAdvisor() {
		DefaultPointcutAdvisor defaultPointcutAdvisor = new DefaultPointcutAdvisor();
		defaultPointcutAdvisor.setAdvice(transactionAdvice());
		defaultPointcutAdvisor.setPointcut(transactionPointcut());
		return defaultPointcutAdvisor;
	}	

	// application components
	@Bean/*(name = "hello")*/
	public UserDaoJdbc userDao() {
		UserDaoJdbc userDaoJdbc = new UserDaoJdbc();
		userDaoJdbc.setDataSource(dataSource());
		return userDaoJdbc;
	}
	
	
	@Bean()
	public UserService userServiceImpl() {
		UserServiceImpl userServiceImpl = new UserServiceImpl();
		userServiceImpl.setUserDao(userDao());
		userServiceImpl.setMailSender(mailSender());
		return userServiceImpl;
	}
	
	// <bean id="testUserService" 
	//       class="springbook.user.service.UserServiceTest$TestUserServiceImpl" parent="userService" />
//	@Bean
//	@Qualifier("testUserService")
//	public UserServiceImpl testUserService() {
//	    UserServiceImpl testUserServiceImpl = new UserServiceTest.TestUserServiceImpl();
//	    testUserServiceImpl.setUserDao(userDao());
//	    testUserServiceImpl.setMailSender(mailSender());
//	    return testUserServiceImpl;
//	}
//	
//	@Bean
//	public UserServiceImpl userService() {
//		UserServiceImpl userServiceImpl = new UserServiceImpl();
//		userServiceImpl.setUserDao(userDao());
//		userServiceImpl.setMailSender(mailSender());		
//		return userServiceImpl;
//	}
//	
	
	@Bean()
	public TxProxyFactoryBean userService()
	{
		TxProxyFactoryBean txProxyFactoryBean = new TxProxyFactoryBean();
		txProxyFactoryBean.setTarget(userServiceImpl());
		txProxyFactoryBean.setServiceInterface(UserService.class);
		txProxyFactoryBean.setTransactionManager(transactionManager());
		txProxyFactoryBean.setPattern("upgrade");
		return txProxyFactoryBean;
	}
	
	@Bean
	public DummyMailSender mailSender() {
		DummyMailSender dummyMailSender = new DummyMailSender();
		return dummyMailSender;
	}
	
	
}