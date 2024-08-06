package com.sj232test.dao;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj232.dao.DaoFactory;

import com.sj232test.domain.User;

public class UserDaoTestSet {
	@Test 
	public void andAndGet() throws SQLException {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);

		User user1 = new User("gyumeedf", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		
		dao.deleteAll();
		assertEquals(dao.getCount(), is(0));
	

		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), is(2));
		
		User userget1 = dao.get(user1.getId());
		assertEquals(userget1.getName(), is(user1.getName()));
		assertEquals(userget1.getPassword(), is(user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertEquals(userget2.getName(), is(user2.getName()));
		assertEquals(userget2.getPassword(), is(user2.getPassword()));
	}
	
	@Test
	public void count() throws SQLException {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", UserDao.class);
		
		User user1 = new User("gyumee", "박성철", "springno1");
		User user2 = new User("leegw700", "이길원", "springno2");
		User user3 = new User("bumjin", "박범진", "springno3");
				
		dao.deleteAll();
		assertEquals(dao.getCount(), is(0));
				
		dao.add(user1);
		assertEquals(dao.getCount(), is(1));
		
		dao.add(user2);
		assertEquals(dao.getCount(), is(2));
		
		dao.add(user3);
		assertEquals(dao.getCount(), is(3));
	}
}
