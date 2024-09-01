package com.AspectjDemo.One.DeclaringAdvice.service;

import java.util.List;

import com.AspectjDemo.One.DeclaringAdvice.service.model.Account;

public interface AccountService {

	void createAccount(Account id);
	
	void deleteAcoount(String id);
	
	Account getAccount(String id);
	
	void updateAccount(Account account);
	
	List<Account> findAccounts(String accountholder);
	
	List<Account> findAccoutName(Account account);
}
