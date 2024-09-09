package com.sj762.springframe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj762.springframe.domain.User;


@Service("userService")
@Transactional
public interface UserService {
	void add(User user);
	void deleteAll();
	void update(User user);
	
	@Transactional(readOnly = true)
	Optional<User> get(String id);	
	
	@Transactional(readOnly = true)
	List<User> getAll();
	
	void upgradeLevels();
}