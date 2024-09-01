package com.AspectjDemo.One.DeclaringAdvice.service.model;

public class Account {
	
	private String id;
	private String ownername;
	private double balace;
	private String accountHolder;
	
	public Account() {}
	
	public Account(String accountHolder) 
	{
		this.setAccountHolder(accountHolder);
	}
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOwnername() {
		return ownername;
	}
	public void setOwnername(String ownername) {
		this.ownername = ownername;
	}
	public double getBalace() {
		return balace;
	}
	public void setBalace(double balace) {
		this.balace = balace;
	}
	public String getAccountHolder() {
		return accountHolder;
	}
	public void setAccountHolder(String accountHolder) {
		this.accountHolder = accountHolder;
	}
	
	@Override
	public String toString()
	{
		return "Accout{"+"accountHolder ='" + 
				accountHolder +'\''+'}';
	}


}
