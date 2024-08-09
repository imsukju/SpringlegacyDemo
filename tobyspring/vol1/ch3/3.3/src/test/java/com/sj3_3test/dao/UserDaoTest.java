package com.sj3_3test.dao;



import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sj3_3.dao.DaoFactory;
import com.sj3_3.dao.UserDao;
import com.sj3_3.domain.User;

@ContextConfiguration(classes = {DaoFactory.class})
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
		
		this.user1 = new User("abc", "test1", "springno1");
		this.user2 = new User("cba", "test2", "springno2");
		this.user3 = new User("aaa", "¹test3", "springno3");

	}
	
	@Test 
	public void andAndGet() throws SQLException {		
		dao.deleteAll();
		assertEquals(dao.getCount(),0);

		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		User userget1 = dao.get(user1.getId());
		assertEquals(userget1.getName(), user1.getName());
		assertEquals(userget1.getPassword(),user1.getPassword());
		
		User userget2 = dao.get(user2.getId());
		assertEquals(userget2.getName(), user2.getName());
		assertEquals(userget2.getPassword(), user2.getPassword());
	}

//	@Test
//	public void getUserFailure() throws SQLException {
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//		
//		dao.get("unknown_id");
//	}
//
//	
//	@Test
//	public void count() throws SQLException {
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//				
//		dao.add(user1);
//		assertThat(dao.getCount(), is(1));
//		
//		dao.add(user2);
//		assertThat(dao.getCount(), is(2));
//		
//		dao.add(user3);
//		assertThat(dao.getCount(), is(3));
//	}
}