package com.AspectjDemo.One.DeclaringAdvice;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.AspectjDemo.One.DeclaringAdvice.config.AppConfigDeclaringAdvice;
import com.AspectjDemo.One.DeclaringAdvice.dao.AccountDao;
import com.AspectjDemo.One.DeclaringAdvice.dao.AccountDaoImpl;
import com.AspectjDemo.One.DeclaringAdvice.service.AccountService;
import com.AspectjDemo.One.DeclaringAdvice.service.AccountServiceImpl;
import com.AspectjDemo.One.DeclaringAdvice.service.MyServiceDeclaringAdvice;
import com.AspectjDemo.One.DeclaringAdvice.service.model.Account;


public class Main {

	
	 public static void checkAroundADviceTroughAccountService(ApplicationContext context) {
	        AccountDao dao = context.getBean(AccountDao.class);
	        AccountService accountservice = context.getBean(AccountService.class);

	        Account validAccount = new Account();
	        validAccount.setId("1234");
	        validAccount.setOwnername("Hello");
	        validAccount.setBalace(1000.0);
	        accountservice.createAccount(validAccount);
	        List<Account> accountarray = accountservice.findAccounts("Hello world");
	        List<Account> accountarray2 = accountservice.findAccoutName(validAccount);
	    }

	    public static void main(String[] args) {
	        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigDeclaringAdvice.class);
	        MyServiceDeclaringAdvice myservice = context.getBean(MyServiceDeclaringAdvice.class);
	        myservice.performTask();
	        checkAroundADviceTroughAccountService(context);


	    }
	}