package com.mypractice.two.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class Abank implements Banks{
	private String name;
	static HashMap<Integer,String> BankAccountNumber = new HashMap<>();
	private int Money;
	
	public Abank(int account ,String name)
	{
		
		BankAccountNumber.put(account, name);
		this.setMoney(5000);
	}

	@Override
	public void depositMoney(HashMap<String, Integer> info, int a) {
//	    Map<String, Integer> matchingEntries = BankAccountNumber.entrySet().stream()
//	    		.filter(
//	    				x -> BankAccountNumber.containsKey(x.getKey()) && BankAccountNumber.containsValue(x.getValue())
//	    				)
//	    		.collect(null);
	    		
		this.setMoney(this.getMoney()-a);
		
	}
	@Override
	public void receiveDeposit(HashMap<String, Integer> info, int a) {
		// TODO Auto-generated method stub
		
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public int getMoney() {
		return Money;
	}
	public void setMoney(int money) {
		Money = money;
	}
	
	
}
