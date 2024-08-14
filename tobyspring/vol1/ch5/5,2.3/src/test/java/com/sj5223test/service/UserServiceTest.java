package com.sj5223test.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.sj5223.dao.DaoFactory;
import com.sj5223.dao.UserDao;
import com.sj5223.domain.Level;
import com.sj5223.domain.User;
import com.sj5223.service.UserService;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class})
public class UserServiceTest {
	@Autowired UserService userService;	
	@Autowired UserDao userDao;
	@Autowired DataSource dataSource;
	
	List<User> users;	// test fixture
	
	@BeforeEach
	public void setUp() {
		users = Arrays.asList(
				new User("madnite1", "이상호", "p4", Level.SILVER, 60, UserService.MIN_RECCOMEND_FOR_GOLD),
				new User("bumjin", "박범진", "p1", Level.BASIC, UserService.MIN_LOGCOUNT_FOR_SILVER-1, 0),
				new User("joytouch", "강명성", "p2", Level.BASIC, UserService.MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p3", Level.SILVER, 60, UserService.MIN_RECCOMEND_FOR_GOLD-1),
				new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
				);
		
		//	public User(String id, String name, String password, Level level,int login, int recommend)
	}

	@Test
	public void upgradeLevels() throws Exception {
		userDao.deleteAll();
		for(User user : users) userDao.add(user);
		
		userService.upgradeLevels();
		
		checkLevelUpgraded(users.get(0), true);
		checkLevelUpgraded(users.get(1), false);
		checkLevelUpgraded(users.get(2), true);
		checkLevelUpgraded(users.get(3), false);
		checkLevelUpgraded(users.get(4), false);
	}

	private void checkLevelUpgraded(User user, boolean upgraded) {
		Optional<User> userUpdate = userDao.get(user.getId());
		if (upgraded) {
			assertEquals(userUpdate.get().getLevel(), user.getLevel().nextLevel());
		}
		else {
			assertEquals(userUpdate.get().getLevel(), user.getLevel());
		}
	}
	
	@Test 
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);	  // GOLD 레벨  
		User userWithoutLevel = users.get(0);  
		userWithoutLevel.setLevel(null);
		
		userService.add(userWithLevel);	  
		userService.add(userWithoutLevel);
		
		Optional<User> userWithLevelRead = userDao.get(userWithLevel.getId());
		Optional<User> userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		
		assertEquals(userWithLevelRead.get().getLevel(), userWithLevel.getLevel()); 
		assertEquals(userWithoutLevelRead.get().getLevel(), (Level.BASIC));
	}
 
	@Test
	public void upgradeAllOrNothing() throws Exception {
		UserService testUserService = new TestUserService(users.get(3).getId());  
		testUserService.setUserDao(this.userDao); 
		testUserService.setDataSource(this.dataSource);
		 
		userDao.deleteAll();			  
		for(User user : users) userDao.add(user);
		
		try {
			testUserService.upgradeLevels();   
			fail("TestUserServiceException expected"); 
		}
		catch(TestUserServiceException e) { 
		}
		
		checkLevelUpgraded(users.get(1), false);
	}

	
	static class TestUserService extends UserService {
		private String id;
		
		private TestUserService(String id) {  
			this.id = id;
		}

		protected void upgradeLevel(User user) {
			if (user.getId().equals(this.id)) 
				throw new TestUserServiceException();  
			super.upgradeLevel(user);  
		}
	}
	
	static class TestUserServiceException extends RuntimeException {
	}
	




}
