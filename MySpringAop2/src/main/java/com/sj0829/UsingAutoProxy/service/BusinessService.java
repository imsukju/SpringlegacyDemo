package com.sj0829.UsingAutoProxy.service;

import org.springframework.transaction.annotation.Transactional;

public interface BusinessService {
	@Transactional
	void process();
}
