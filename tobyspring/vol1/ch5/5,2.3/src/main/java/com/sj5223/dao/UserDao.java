package com.sj5223.dao;

import java.util.List;
import java.util.Optional;

import com.sj5223.domain.User;




public interface UserDao {
	
	void add(User user);

	Optional<User> get(String id);

	List<User> getAll();

	void deleteAll();

	int getCount();

	void update(User user);

}