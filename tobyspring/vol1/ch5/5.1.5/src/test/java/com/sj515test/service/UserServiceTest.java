//package com.sj515test.service;
//
//import java.util.Arrays;
//import java.util.List;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit.jupiter.SpringExtension;
//
//import com.sj515.dao.DaoFactory;
//import com.sj515.dao.UserDao;
//import com.sj515.domain.Level;
//import com.sj515.domain.User;
//import com.sj515.service.UserService;
//
//@ExtendWith(SpringExtension.class)
//@ContextConfiguration(classes = {DaoFactory.class})
////public class UserServiceTest {
////	@Autowired 	UserService userService;	
////	@Autowired UserDao userDao;
////	
////	List<User> users;	// test fixture
////	
////	@BeforeEach
////	public void setUp() {
////		users = Arrays.asList(
////				new User("bumjin", "박범진", "p1", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER-1, 0),
////				new User("joytouch", "강명성", "p2", Level.BASIC, MIN_LOGCOUNT_FOR_SILVER, 0),
////				new User("erwins", "신승한", "p3", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD-1),
////				new User("madnite1", "이상호", "p4", Level.SILVER, 60, MIN_RECCOMEND_FOR_GOLD),
////				new User("green", "오민규", "p5", Level.GOLD, 100, Integer.MAX_VALUE)
////				);
////	}
////
////	@Test
////	public void upgradeLevels() {
////		userDao.deleteAll();
////		for(User user : users) userDao.add(user);
////		
////		userService.upgradeLevels();
////		
////		checkLevelUpgraded(users.get(0), false);
////		checkLevelUpgraded(users.get(1), true);
////		checkLevelUpgraded(users.get(2), false);
////		checkLevelUpgraded(users.get(3), true);
////		checkLevelUpgraded(users.get(4), false);
////	}
////
////	private void checkLevelUpgraded(User user, boolean upgraded) {
////		User userUpdate = userDao.get(user.getId());
////		if (upgraded) {
////			assertThat(userUpdate.getLevel(), is(user.getLevel().nextLevel()));
////		}
////		else {
////			assertThat(userUpdate.getLevel(), is(user.getLevel()));
////		}
////	}
////
////	@Test 
////	public void add() {
////		userDao.deleteAll();
////		
////		User userWithLevel = users.get(4);	  // GOLD 레벨  
////		User userWithoutLevel = users.get(0);  
////		userWithoutLevel.setLevel(null);
////		
////		userService.add(userWithLevel);	  
////		userService.add(userWithoutLevel);
////		
////		User userWithLevelRead = userDao.get(userWithLevel.getId());
////		User userWithoutLevelRead = userDao.get(userWithoutLevel.getId());
////		
////		assertThat(userWithLevelRead.getLevel(), is(userWithLevel.getLevel())); 
////		assertThat(userWithoutLevelRead.getLevel(), is(Level.BASIC));
////	}
////}