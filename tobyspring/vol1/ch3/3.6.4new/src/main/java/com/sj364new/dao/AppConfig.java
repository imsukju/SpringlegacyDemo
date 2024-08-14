package com.sj364new.dao;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

@Configuration
public class AppConfig {

	@Bean
	public DataSource datasource()
	{
		SimpleDriverDataSource datasource = new SimpleDriverDataSource();
		
		//com.mysql.cj.jdbc.Driver.class
		datasource.setDriverClass(com.mysql.cj.jdbc.Driver.class);
		datasource.setUrl("jdbc:mysql://localhost/sbdt_db?characterEncoding=UTF-8");
		datasource.setUsername("root");
		datasource.setPassword("1234");
		
		return datasource;
	}
	
	@Bean
	public UserDao userDao()
	{
		UserDao dao = new UserDao();
		dao.setDataSource(datasource());
		return dao;
	}
}
