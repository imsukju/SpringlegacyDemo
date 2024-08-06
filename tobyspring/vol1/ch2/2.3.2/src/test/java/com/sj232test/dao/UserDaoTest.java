package com.sj232test.dao;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj232.dao.DaoFactory;
import com.sj232.dao.UserDao;
import com.sj232.domain.User;



public class UserDaoTest {	
	
	@Test
	public void addAndGet() throws SQLException, ClassNotFoundException {				
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class);
		UserDao dao = context.getBean("userDao", com.sj232.dao.UserDao.class);
		
		dao.deleteAll();	
		//Asserts that two longs are equal. If they are not, an AssertionError is thrown. 
		// Assertion 메소드들 중에 하나인,
		assertEquals(dao.getCount(), 0);
		
		User user = new User();
		user.setId("gyumee");
		user.setName("박성철");
		user.setPassword("springno1");
		dao.add(user);
		assertEquals(dao.getCount(), 1);
		
		User user2 = dao.get(user.getId());		
		
		assertEquals(user2.getName(), user.getName());
		assertEquals(user2.getPassword(), user.getPassword());		
	}
}