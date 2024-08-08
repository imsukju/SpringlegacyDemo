package com.sj2.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;
import org.junit.jupiter.api.Assertions;
import  org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import com.sj2.domain.User;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest2 {
	static User user3 = new User();
	

	
	static void setuser3(String id, String name, String password)
	{
		user3.setId(id);
		user3.setName(name);
		user3.setPassword(password);
	}
	

	public void andAndGet() throws SQLException {

		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoImple.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user1 = new User("yhtah", "박성철", "springno1");
		User user2 = new User("hayrehrae", "이길원", "springno2");
		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
	

		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		User userget1 = dao.get(user1.getId());
		assertEquals(userget1.getName(), user1.getName());
		assertEquals(userget1.getPassword(), user1.getPassword());
		
		User userget2 = dao.get(user2.getId());
		assertEquals(userget2.getName(), user2.getName());
		assertEquals(userget2.getPassword(), user2.getPassword());
		
		
	}
//	@Test
//	@Order(1)
	public void count() throws SQLException {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoImple.class); //여기서 빈 dataSource 호출됨
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		dao.deleteAll();
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		User user3 = new User("bumjin", "박범진", "springno3");
				
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
				
		dao.add(user1);
		assertEquals(dao.getCount(), 1);
		
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		dao.add(user3);
		assertEquals(dao.getCount(), 3);
		

	}
	
//	@Test
//	@Order(2)
	public void mysqlquery() throws SQLException
	{
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoImple.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
	
		String c = dao.updateis("users","id");
		assertEquals(c, "김가나", "NO");
		Assertions.assertThrows(EmptyResultDataAccessException.class,
				() -> dao.get("unknown_id"));
		
	}
	
	@Test
	@Order(3)
	public void mysqlquery2() throws SQLException
	{
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoImple.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
	
		dao.deleteAll();
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		User user3 = new User("bumjin", "박범진", "springno3");
		dao.add(user1);
		dao.add(user2);
		dao.add(user3);
		
		dao.deletecolumn("users","id");
		assertEquals(dao.getCount(),2);
		
	}	
}
