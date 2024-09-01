package com.AspectjDemo.One.DeclaringAdvice.dao;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.AspectjDemo.One.DeclaringAdvice.service.model.Account;

@Repository
public class AccountDaoImpl implements AccountDao {
	
	private Map<String, Account> accounts = new HashMap<>();

	@Override
	public Account findAccountByid(String id) {
		// TODO Auto-generated method stub
		return accounts.get(id);
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		accounts.put(account.getId(), account);

	}

}
