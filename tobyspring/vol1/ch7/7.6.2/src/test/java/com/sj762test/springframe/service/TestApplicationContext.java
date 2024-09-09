package com.sj762test.springframe.service;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.mail.MailSender;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;


import com.mysql.cj.jdbc.Driver;
import com.sj762.springframe.dao.UserDao;
import com.sj762.springframe.service.DummyMailSender;
import com.sj762.springframe.service.UserService;
import com.sj762.springframe.service.UserServiceImpl;
import com.sj762.springframe.sqlservice.SqlRegistry;
import com.sj762.springframe.sqlservice.SqlService;
import com.sj762.springframe.sqlservice.updatable.EmbeddedDbSqlRegistry;
import com.sj762test.springframe.service.UserServiceTest.TestUserServiceException;

//@Configuration
@EnableTransactionManagement
@ComponentScan(basePackages = "com.sj762.springframe")
public class TestApplicationContext {
	/**
	 * DB연결과 트랜잭션
	 */
	
	@Bean
	@Qualifier("jdbcDataSource")
	public DataSource dataSource() {
		SimpleDriverDataSource ds = new SimpleDriverDataSource();
		ds.setDriverClass(Driver.class);
		ds.setUrl("jdbc:mysql://localhost:3306/sbdt_db?characterEncoding=UTF-8");
		ds.setUsername("root");
		ds.setPassword("1234");
		return ds;
	}
	
	@Bean
	public PlatformTransactionManager transactionManager() {
		DataSourceTransactionManager tm = new DataSourceTransactionManager();
		tm.setDataSource(dataSource());
		return tm;
	}
	
	/**
	 * 애플리케이션 로직 & 테스트용 빈
	 */
	
	@Autowired 
	UserDao userDao;		
	
	
	@Bean
	@Qualifier("testUserService")
	public UserService testUserService() {
	    UserServiceImpl testUserServiceImpl = new UserServiceTest.TestUserServiceImpl();
	    testUserServiceImpl.setUserDao(userDao);
	    testUserServiceImpl.setMailSender(mailSender());
	    return testUserServiceImpl;
	}
	
	@Bean
	public MailSender mailSender() {
		return new DummyMailSender();
	}
	
	/**
	 * SQL서비스
	 */
	
//	@Bean
//	public SqlService sqlService() {
//		OxmSqlService sqlService = new OxmSqlService();
//		sqlService.setUnmarshaller(unmarshaller());
//		sqlService.setSqlRegistry(sqlRegistry());
//		return sqlService;
//	}
	
	@Bean
	public SqlRegistry sqlRegistry() {
		EmbeddedDbSqlRegistry sqlRegistry = new EmbeddedDbSqlRegistry();
		sqlRegistry.setDataSource(embeddedDatabase());
		return sqlRegistry;
	}
	
//	@Bean
//	public Unmarshaller unmarshaller() {
//		Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
//		marshaller.setContextPath("com.intheeast.springframe.sqlservice.jaxb");
//		return marshaller;
//	}
	
	@Bean	
	public DataSource embeddedDatabase() {
		return new EmbeddedDatabaseBuilder()
			.setName("embeddedDatabase")
			.setType(EmbeddedDatabaseType.H2)
			.addScript("sqlRegistrySchema.sql")
			.build();
	}
}