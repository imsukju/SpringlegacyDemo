package com.sj652.service;

import com.sj652.domain.User;

public interface UserService {
	void add(User user);
	void upgradeLevels();
}