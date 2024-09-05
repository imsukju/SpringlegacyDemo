package com.sj664.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sj664.domain.User;

@Service
@Transactional
public interface UserService {
	@Transactional
	void add(User user);
	@Transactional
	void deleteAll();
	@Transactional
	void update(User user);		
	Optional<User> get(String id);	
	List<User> getAll();
	@Transactional
	void upgradeLevels();
}