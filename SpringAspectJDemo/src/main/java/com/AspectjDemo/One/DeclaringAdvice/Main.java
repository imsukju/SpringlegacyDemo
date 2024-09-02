package com.AspectjDemo.One.DeclaringAdvice;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.AspectjDemo.One.DeclaringAdvice.aop.ExecutionCountingAspectdeclaring;
import com.AspectjDemo.One.DeclaringAdvice.config.AppConfigDeclaringAdvice;
import com.AspectjDemo.One.DeclaringAdvice.dao.AccountDao;
import com.AspectjDemo.One.DeclaringAdvice.dao.AccountDaoImpl;
import com.AspectjDemo.One.DeclaringAdvice.service.AccountService;
import com.AspectjDemo.One.DeclaringAdvice.service.AccountServiceImpl;
import com.AspectjDemo.One.DeclaringAdvice.service.MyServiceDeclaringAdvice;
import com.AspectjDemo.One.DeclaringAdvice.service.SampleserviceDeclaringAdvice;
import com.AspectjDemo.One.DeclaringAdvice.service.model.Account;
import com.AspectjDemo.One.DeclaringAdvice.service.model.MyType;
import com.AspectjDemo.One.DeclaringAdvice.service.model.UsageTrackedDeclare;


public class Main {

	
	 public static void checkAroundADviceTroughAccountService(ApplicationContext context) {
	        AccountDao dao = context.getBean(AccountDao.class);
	        AccountService accountservice = context.getBean("accountServiceImpl",AccountService.class);

	        Account validAccount = new Account();
	        validAccount.setId("1234");
	        validAccount.setOwnername("Hello");
	        validAccount.setBalace(1000.0);
	        accountservice.createAccount(validAccount);
	        accountservice.updateAccount(validAccount);
	        List<Account> accountarray = accountservice.findAccounts("Hello world");
	        List<Account> accountarray2 = accountservice.findAccoutName(validAccount);
	    }
	 
	 public static void checkAroundADviceTroughAccountService2(ApplicationContext context)
	 {
		 SampleserviceDeclaringAdvice sampleserviceDeclaringAdvice = (SampleserviceDeclaringAdvice)context.getBean("sampleserviceDeclaringAdvice");
		 MyType myTypeInstance = new MyType("example");
		 sampleserviceDeclaringAdvice.sampleGenericCollectionMethod(Arrays.asList(myTypeInstance));
		 sampleserviceDeclaringAdvice.sampleGenericMethod(myTypeInstance);
		 
	 }
	 
	 public static void checkAutomicThreadSafe(ApplicationContext context)
	 {
		 MyServiceDeclaringAdvice myservice = (MyServiceDeclaringAdvice)context.getBean("myServiceDeclaringAdvice");
		 
		 Runnable tast = () -> myservice.performTask();
		 
		 Thread thread1 = new Thread(tast);
		 Thread thread2 = new Thread(tast);
		 Thread thread3 = new Thread(tast);
		 Thread thread4 = new Thread(tast);
		 Thread thread5 = new Thread(tast);
		 
		 thread1.start();
		 System.out.println("thread1 시작");
		 thread2.start();
		 System.out.println("thread2 시작");
		 thread3.start();
		 System.out.println("thread3 시작");
		 thread4.start();
		 System.out.println("thread4 시작");
		 thread5.start();
		 System.out.println("thread5 시작");
		 try
		 {
			 thread1.join(); // 쓰레드가 죽을때까지 기다리는거
			 thread2.join();
			 thread3.join();
			 thread4.join();
			 thread5.join();
		 }
		 catch(InterruptedException ex)
		 {
			ex.printStackTrace();
		 }
		 
		 ExecutionCountingAspectdeclaring countAspect = context.getBean(ExecutionCountingAspectdeclaring.class);
		 System.out.println("Total execution count: " + countAspect.getExecutionCount());
	 }
	 
	 public static void checkIntrod(ApplicationContext context)
	 {
		 MyServiceDeclaringAdvice myservice = (MyServiceDeclaringAdvice)context.getBean("myServiceDeclaringAdvice");
		 
	        AccountDao dao = context.getBean(AccountDao.class);
	        AccountService accountservice = context.getBean("accountServiceImpl",AccountService.class);

	        Account validAccount = new Account();
	        validAccount.setId("1234");
	        validAccount.setOwnername("Hello");
	        validAccount.setBalace(1000.0);
	        accountservice.createAccount(validAccount);
	        accountservice.updateAccount(validAccount);
	        List<Account> accountarray = accountservice.findAccounts("Hello world");
	        List<Account> accountarray2 = accountservice.findAccoutName(validAccount);
	        UsageTrackedDeclare usageTracked = (UsageTrackedDeclare)accountservice;
	        
	        System.out.println("AccountService usage count : " + usageTracked.getUseCount());
	        validAccount.setId("1234");
	        validAccount.setOwnername("Hello");
	        validAccount.setBalace(1000.0);
	        accountservice.createAccount(validAccount);
	        accountservice.updateAccount(validAccount);

	 }

    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigDeclaringAdvice.class);
        MyServiceDeclaringAdvice myservice = context.getBean(MyServiceDeclaringAdvice.class);
//	      myservice.performTask();
//	      checkAroundADviceTroughAccountService(context);
//	      checkAroundADviceTroughAccountService2(context);
//        checkAutomicThreadSafe(context);
        checkIntrod(context);

    }
	}