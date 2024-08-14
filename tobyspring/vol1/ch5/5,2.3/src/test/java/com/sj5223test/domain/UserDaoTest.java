package com.sj5223test.domain;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import javax.sql.DataSource;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator;
import org.springframework.jdbc.support.SQLExceptionTranslator;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.mysql.cj.exceptions.MysqlErrorNumbers;
import com.sj5223.dao.DaoFactory;
import com.sj5223.dao.UserDao;
import com.sj5223.domain.Level;
import com.sj5223.domain.User;



@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {DaoFactory.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDaoTest { 
	@Autowired
	private UserDao dao;
	@Autowired
	private DataSource dataSource;
	
	private User user1;
	private User user2;
	private User user3;
	
		
	User user;
	
	@BeforeEach
	public void setUp() {
		user = new User();
	}
	
	@Test
	public void upgradeLevel() {
		Level[] levels = Level.values();
		for(Level level : levels) {
			if (level.nextLevel() == null) continue;
			user.setLevel(level);
			user.upgradeLevel();
			assertEquals(user.getLevel(), level.nextLevel());
		}
	}
	
	@Test
	public void cannotUpgradeLevel() {
		Level[] levels = Level.values();
		for(Level level : levels) {
			if (level.nextLevel() != null) continue;
			user.setLevel(level);
			user.upgradeLevel();
			
		Assertions.assertThrows(IllegalStateException.class, 
				() -> {user.upgradeLevel();});
		}
	}
	
	
	


}