package com.AspectjDemo.One.DeclaringAdvice.service;

import java.util.Arrays;
import java.util.List;
import static java.lang.System.out;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.AspectjDemo.One.DeclaringAdvice.annotation.Auditable;
import com.AspectjDemo.One.DeclaringAdvice.dao.AccountDao;
import com.AspectjDemo.One.DeclaringAdvice.service.model.Account;

@Auditable("accountServiceClass")
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private AccountDao accountDao;

	@Override
	public void createAccount(Account id) {
		out.println("Account Created : " + id);

	}

	@Override
	public void deleteAcoount(String id) {
		out.println("Account Deleted : " + id);

	}

	@Override
	public Account getAccount(String id) {
		if(id == null)
		{
			throw new IllegalArgumentException("Account ID cannot to be null");
		}
		return accountDao.findAccountByid(id);
	}

	@Auditable("accountUpdate")
	@Override
	public void updateAccount(Account account) {
		if (account.getBalace() <0 )
		{
			throw new IllegalArgumentException("Account balace cannot to be negative");
		}
		
		out.println("Account Updated : " + account);
		accountDao.updateAccount(account);

	}

	@Override
	public List<Account> findAccounts(String accountholder) {
		// TODO Auto-generated method stub
		out.println("Finding accounts with pattern: " + accountholder);
		return Arrays.asList(new Account("sj"), new Account("js"));
	}

	@Override
	public List<Account> findAccoutName(Account account) {
		// TODO Auto-generated method stub
		return Arrays.asList(new Account("sj"), new Account("js"));
	}

}
