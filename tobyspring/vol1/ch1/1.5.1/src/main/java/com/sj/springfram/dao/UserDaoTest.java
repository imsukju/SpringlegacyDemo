package com.sj.springfram.dao;

import java.sql.SQLException;

import org.springframework.beans.BeansException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj.springfram.domain.User;

public class UserDaoTest {
	
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		// 어노테이션 기반의 configuration metadata 사용하기 위한 코드
		try (AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class)) {
			UserDao dao = context.getBean("userDao", UserDao.class);

			User user = new User();
			user.setId("gath");
			user.setName("백기선");
			user.setPassword("married");

			dao.add(user);
				
			System.out.println(user.getId() + " 등록 성공");
			
			User user2 = dao.get(user.getId());
			System.out.println(user2.getName());
			System.out.println(user2.getPassword());
				
			System.out.println(user2.getId() + " 조회 성공");
		} catch (BeansException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
