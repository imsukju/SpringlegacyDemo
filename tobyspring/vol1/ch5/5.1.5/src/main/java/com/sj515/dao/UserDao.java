package com.sj515.dao;

import java.util.List;
import java.util.Optional;

import com.sj515.domain.User;



public interface UserDao {
	
	void add(User user);

	Optional<User> get(String id);

	List<User> getAll();

	void deleteAll();

	int getCount();

	void update(User user);

}