package com.sj364newtest.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sj364new.dao.AppConfig;
import com.sj364new.dao.UserDao;
import com.sj364new.domain.User;

@ContextConfiguration(classes = {AppConfig.class})
@ExtendWith(SpringExtension.class)
@DirtiesContext
public class UserDaoTest {
	@Autowired
	ApplicationContext context;
	
	private UserDao dao; 
	
	private User user1;
	private User user2;
	private User user3;
	
	@BeforeEach
	public void setUp() {
		this.dao = this.context.getBean("userDao", UserDao.class);
		
		this.user1 = new User("gyumee", "afdg", "springno1");
		this.user2 = new User("leegw700", "afgfag", "springno2");
		this.user3 = new User("bumjin", "adghgdh", "springno3");

	}
	
	@Test 
	public void andAndGet() {		
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

	@Test()
	public void getUserFailure() throws SQLException {
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
		
		Assertions.assertThrows(EmptyResultDataAccessException.class, 
				() -> {dao.get("unknown_id");});
	}

	
	@Test
	public void count() {
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);
				
		dao.add(user1);
		assertEquals(dao.getCount(), 1);
		
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		dao.add(user3);
		assertEquals(dao.getCount(), 3);
	}
	
	@Test
	public void getAll()  {
		dao.deleteAll();
		
		List<User> users0 = dao.getAll();
		assertEquals(users0.size(), 0);
		
		dao.add(user1); // Id: gyumee
		List<User> users1 = dao.getAll();
		assertEquals(users1.size(), 1);
		checkSameUser(user1, users1.get(0));
		
		dao.add(user2); // Id: leegw700
		List<User> users2 = dao.getAll();
		assertEquals(users2.size(), 2);
		checkSameUser(user1, users2.get(0));  
		checkSameUser(user2, users2.get(1));
		
		dao.add(user3); // Id: bumjin
		List<User> users3 = dao.getAll();
		assertEquals(users3.size(), 3);
		checkSameUser(user3, users3.get(0));  
		checkSameUser(user1, users3.get(1));  
		checkSameUser(user2, users3.get(2));  
	}

	private void checkSameUser(User user1, User user2) {
		assertEquals(user1.getId(), user2.getId());
		assertEquals(user1.getName(), user2.getName());
		assertEquals(user1.getPassword(), user2.getPassword());
	}

}
