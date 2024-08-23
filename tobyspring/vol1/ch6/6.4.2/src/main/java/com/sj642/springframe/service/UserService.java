package com.sj642.springframe.service;

import com.sj642.springframe.domain.User;

public interface UserService {
	void add(User user);
	void upgradeLevels();
}