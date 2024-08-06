package com.sj235test.dao;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.sql.SQLException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.dao.EmptyResultDataAccessException;

import com.sj235test.domain.User;


//@TestMethodOrder(MethodOrderer.DisplayName.class)
public class UserDaoTest {
	private UserDao dao;  
	
	private User user1; //Test Fixture
	private User user2; //Test Fixture
	private User user3; //Test Fixture
	
	@BeforeAll
	public static void beforeall()
	{
		System.out.println("a");
	}
	
	@BeforeEach
	public void setUp() {
		ApplicationContext context =
				new AnnotationConfigApplicationContext(DaoFactory.class);
		this.dao = context.getBean("userDao", UserDao.class);
		
		this.user1 = new User("aaaa", "ahh", "springno1");
		this.user2 = new User("bbbbb", "abab", "springno2");
		this.user3 = new User("ccccc", "abga", "springno3");
		
		  // 설정 파일 또는 환경 변수의 초기화
		System.setProperty("configFile", "test-config.properties");

	}
	
	@Test 
	public void andAndGet() throws SQLException {		
		dao.deleteAll();
		assertEquals(dao.getCount(),0);

		dao.add(user1);
		dao.add(user2);
		assertEquals(dao.getCount(),2);
		
		User userget1 = dao.get(user1.getId());
		assertEquals(userget1.getName(), (user1.getName()));
		assertEquals(userget1.getPassword(), (user1.getPassword()));
		
		User userget2 = dao.get(user2.getId());
		assertEquals(userget2.getName(), (user2.getName()));
		assertEquals(userget2.getPassword(), (user2.getPassword()));
	}

//	@Test /* (expected=EmptyResultDataAccessException.class) */
//	public void getUserFailure() throws SQLException {
//		dao.deleteAll();
//		assertThat(dao.getCount(), is(0));
//		
//		dao.get("unknown_id");
//	}
	
	@Test
	public void getUserFailure() throws SQLException, ClassNotFoundException {		
		dao.deleteAll();
		assertEquals(dao.getCount(), 0);	
		
//		public interface Executable {
//
//			void execute() throws Throwable;
//
//		}
		
		Assertions.assertThrows(EmptyResultDataAccessException.class,
				// Excutable 인터페이스의 excute 메소드의 람다!!!
				() -> dao.get("unknown_id"));
	}
	
	@Test
	public void count() throws SQLException {
		dao.deleteAll();
		assertEquals(dao.getCount(),0);
				
		dao.add(user1);
		assertEquals(dao.getCount(), 1);
		
		dao.add(user2);
		assertEquals(dao.getCount(), 2);
		
		dao.add(user3);
		assertEquals(dao.getCount(), 3);
	}
	
	@AfterEach
    void afterea() {
        // 다른 설정 초기화
        System.clearProperty("configFile");
	}
	@AfterAll
	public static void afterall()
	{
		System.out.println("z");
	}
}
