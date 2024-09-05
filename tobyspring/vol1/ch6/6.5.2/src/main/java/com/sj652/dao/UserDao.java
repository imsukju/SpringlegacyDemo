package com.sj652.dao;

import java.util.List;
import java.util.Optional;

import com.sj652.domain.User;

public interface UserDao {
	
	void add(User user);

	Optional<User> get(String id);

	List<User> getAll();

	void deleteAll();

	int getCount();
	
	public void update(User user);
}