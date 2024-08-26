package com.sj624test.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.sj624.dao.UserDao;
import com.sj624.domain.Level;
import com.sj624.domain.User;
import com.sj624.service.UserService;
import com.sj624.service.UserServiceImpl;
import com.sj624.service.UserServiceTx;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestServiceFactory.class})
public class UserServiceTest {
	@Qualifier("userService2")	@Autowired UserService userService2;	
	@Autowired UserDao userDao;	
	@Qualifier("userService1") @Autowired UserService userService1;
	@Autowired MailSender mailSender; 
	@Autowired PlatformTransactionManager transactionManager;
	
	static List<User> users;	// test fixture
	
	@BeforeEach
	public void setUp() {	
		
		users = Arrays.asList(
				new User("bumjin", "박범진", "p1", "user1@ksug.org", Level.BASIC, UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER-1, 0),
				new User("joytouch", "강명성", "p2", "user2@ksug.org", Level.BASIC, UserServiceImpl.MIN_LOGCOUNT_FOR_SILVER, 0),
				new User("erwins", "신승한", "p3", "user3@ksug.org", Level.SILVER, 60, UserServiceImpl.MIN_RECCOMEND_FOR_GOLD-1),
				new User("madnite1", "이상호", "p4", "user4@ksug.org", Level.SILVER, 60, UserServiceImpl.MIN_RECCOMEND_FOR_GOLD),
				new User("green", "오민규", "p5", "user5@ksug.org", Level.GOLD, 100, Integer.MAX_VALUE)
				);
	}	
	
	@Test @DirtiesContext
	public void upgradeLevels() throws Exception {
		UserServiceImpl userServiceImpl = new UserServiceImpl(); 
		
		MockUserDao mockUserDao = new MockUserDao(this.users);  
		userServiceImpl.setUserDao(mockUserDao);
		
		MockMailSender mockMailSender = new MockMailSender();
		userServiceImpl.setMailSender(mockMailSender);  
				
		userServiceImpl.upgradeLevels();
		
		List<User> updated = mockUserDao.getUpdated();  
		assertEquals(updated.size(), 2);
		checkUserAndLevel(updated.get(0), "joytouch", Level.SILVER); 
		checkUserAndLevel(updated.get(1), "madnite1", Level.GOLD);
		
		List<String> request = mockMailSender.getRequests();  
		assertEquals(request.size(), 2);
		assertEquals(request.get(0), users.get(1).getEmail());
		assertEquals(request.get(1), users.get(3).getEmail());		
	}
	
	private void checkUserAndLevel(User updated, String expectedId, Level expectedLevel) { 
		assertEquals(updated.getId(), expectedId);
		assertEquals(updated.getLevel(), expectedLevel);
	}
	
	static class MockUserDao implements UserDao 
	{ 
		private List<User> users;  
		private List<User> updated = new ArrayList(); 
		
		private MockUserDao(List<User> users) 
		{
			this.users = users;
		}

		public List<User> getUpdated()
		{
			return this.updated;
		}

		public List<User> getAll() 
		{  
			return this.users;
		}

		public void update(User user)
		{  
			updated.add(user);
		}
		
		public void add(User user) { throw new UnsupportedOperationException(); }
		public void deleteAll() { throw new UnsupportedOperationException(); }
		public Optional<User> get(String id) { throw new UnsupportedOperationException(); }
		public int getCount() { throw new UnsupportedOperationException(); }

		@Override
		public DataSource getdatasource() 
		{
			// TODO Auto-generated method stub
			return this.getdatasource();
		}
	}
	
	static class MockMailSender implements MailSender 
	{
		private List<String> requests = new ArrayList<String>();	
		
		public List<String> getRequests() {
			return requests;
		}

		public void send(SimpleMailMessage mailMessage) throws MailException {
			requests.add(mailMessage.getTo()[0]);  
		}

		public void send(SimpleMailMessage... mailMessages) throws MailException {
			for (SimpleMailMessage mailMessage : mailMessages) {
	            //requests.add(mailMessage.getTo()[0]);
	        }		
		}
	}
	
	@Test
	public void mockUpgradeLevels() throws Exception 
	{
		UserServiceImpl userServiceImpl = new UserServiceImpl();

		UserDao mockUserDao = mock(UserDao.class);	    
		when(mockUserDao.getAll()).thenReturn(this.users);
		userServiceImpl.setUserDao(mockUserDao);

		MailSender mockMailSender = mock(MailSender.class);  
		userServiceImpl.setMailSender(mockMailSender);

		userServiceImpl.upgradeLevels();

		verify(mockUserDao, times(2)).update(any(User.class));				  
		//verify(mockUserDao, times(2)).update(any(User.class));
		verify(mockUserDao).update(users.get(1));
		assertEquals(users.get(1).getLevel(), Level.SILVER);
		verify(mockUserDao).update(users.get(3));
		assertEquals(users.get(3).getLevel(), Level.GOLD);

		ArgumentCaptor<SimpleMailMessage> mailMessageArg = ArgumentCaptor.forClass(SimpleMailMessage.class);  
		verify(mockMailSender, times(2)).send(mailMessageArg.capture());
		List<SimpleMailMessage> mailMessages = mailMessageArg.getAllValues();
		assertEquals(mailMessages.get(0).getTo()[0], users.get(1).getEmail());
		assertEquals(mailMessages.get(1).getTo()[0], users.get(3).getEmail());
	}

	private void checkLevelUpgraded(User user, boolean upgraded) {
		Optional<User> optionalUser = userDao.get(user.getId());
		if (!optionalUser.isEmpty()) {
			User userUpdate = optionalUser.get();
			if (upgraded) {
				assertEquals(userUpdate.getLevel(), user.getLevel().nextLevel());
			}
			else {
				assertEquals(userUpdate.getLevel(), (user.getLevel()));
			}			
		}		
	}
	
	@Test
	public void add() {
		userDao.deleteAll();
		
		User userWithLevel = users.get(4);	  // GOLD ����  
		User userWithoutLevel = users.get(0);  
		userWithoutLevel.setLevel(null);
		
		userService2.add(userWithLevel);	  
		userService2.add(userWithoutLevel);
		
		Optional<User> optionalUserWithLevelRead = userDao.get(userWithLevel.getId());
		if (!optionalUserWithLevelRead.isEmpty()) {
			User userWithLevelRead = optionalUserWithLevelRead.get();
			assertEquals(userWithLevelRead.getLevel(), userWithLevel.getLevel()); 
		}		
		
		Optional<User> optionalUserWithoutLevelRead = userDao.get(userWithoutLevel.getId());
		if (!optionalUserWithoutLevelRead.isEmpty()) {
			User userWithoutLevelRead = optionalUserWithoutLevelRead.get();
			assertEquals(userWithoutLevelRead.getLevel(), Level.BASIC);
		}		
	}
	
	@Test
	public void upgradeAllOrNothing() throws Exception 
	{
		TestUserService testUserService = 
				new TestUserService(users.get(2).getId()); 		
		
		testUserService.setUserService(this.userService1/*userService1*/);
		testUserService.setTransactionManager(this.transactionManager);
		testUserService.setUsImpl((UserServiceImpl)testUserService.getUserService());
		
		userDao.deleteAll(); 
		for(User user : users) userDao.add(user);
		
		try {
			testUserService.upgradeLevels();			
			
		}
		catch(TestUserServiceException e) { 
			e.printStackTrace();
			
			fail("TestUserServiceException expected"); 
		}
		
		checkLevelUpgraded(users.get(1), false);
	}
	static class TestUserServiceException extends RuntimeException
	{
	}
	
	
	static class TestUserService extends UserServiceTx
	{
		private String id;
		UserServiceImpl usImpl;
		
		public void setUsImpl(UserServiceImpl usImpl) {
			this.usImpl = usImpl;
		}
		
		private TestUserService(String id)
		{  
			this.id = id;
		}

		@Override
		public void upgradeLevels() {
			TransactionStatus status = this.getTransactionManager().
					getTransaction(new DefaultTransactionDefinition());					
			
			try {
				for (User user : users) {
					if (usImpl.canUpgradeLevel(user)) {
						upgradeLevel(user);
					}
				}
				this.getTransactionManager().commit(status);
			} catch (RuntimeException e) {
				this.getTransactionManager().rollback(status);
				throw e;
			}
		}
		
		protected void upgradeLevel(User user) {
			if (user.getId().equals(this.id)) 
				throw new TestUserServiceException();  
			this.usImpl.upgradeLevel(user);			
		}
	}
	
}