package com.AspectjDemo.One.DeclaringAdvice.dao;

import com.AspectjDemo.One.DeclaringAdvice.service.model.Account;

public interface AccountDao 
{
	Account findAccountByid(String id);
	void updateAccount(Account account);
}
