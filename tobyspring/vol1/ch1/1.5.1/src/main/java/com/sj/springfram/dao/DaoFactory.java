package com.sj.springfram.dao;

//AnnotationConfigApplicationContext 를 구현한 구체


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

// 어노테이션 기반의 configuration metadata
@Configuration
public class DaoFactory {
	
	@Bean
	public UserDao userDao() {
		UserDao dao = new UserDao(connectionMMaker()); // 디펜더 인젝션 dependent injection
													 //constructor argument
		// 일반적인 메소드 호출식이 아니라 디펜더시 인젝션

		return dao;

	}

	@Bean 
	public ConnectionMaker connectionMMaker() {
		ConnectionMaker connectionMaker = new MConnectionMaker();
		return connectionMaker;
	}
	
	@Bean 
	public ConnectionMaker connectionHMaker() {
		ConnectionMaker connectionMaker = new HConnectionMaker();
		return connectionMaker;
	}
	

}
