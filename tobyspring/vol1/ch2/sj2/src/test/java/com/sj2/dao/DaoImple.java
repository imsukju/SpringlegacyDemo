package com.sj2.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;
@Configuration
public class DaoImple<K,V> implements DaoInter<DataSource, UserDao>{
	SimpleDriverDataSource dataSource = new SimpleDriverDataSource (); //SimpleDriverDataSource 는 DataSource 를 구현한 구체를 상속받은 클래스
	SimpleDriverDataSource HdataSource = new SimpleDriverDataSource ();	

	public void setconfigM()
	{
		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/"
				+ "hi?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");
	}

	public void setconfigH()
	{
		HdataSource.setDriverClass(org.h2.Driver.class);
		HdataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		HdataSource.setUsername("sa");
		HdataSource.setPassword("1234");
	}	
	
	@Bean
	@Override
	public DataSource dataSource() {
		

		Scanner sc = new Scanner(System.in);
		System.out.println("mysql or h2 or all 선택");
		String choice = sc.nextLine();

		List<DataSource> configset = new ArrayList<>();
		configset.add(dataSource);
		configset.add(HdataSource);
		
		if(choice.equals("mysql"))
		{	
			this.setconfigM();
			return configset.get(0);
		
		}
		else if(choice.equals("h2"))
		{
			this.setconfigH();
			return configset.get(1);
		}
		return null;
	}
	@Bean
	@Override
	public UserDao userDao() {
		
		UserDao userDao = new UserDao();
		userDao.setDataSource(this.dataSource());
		return userDao;
	}

}
