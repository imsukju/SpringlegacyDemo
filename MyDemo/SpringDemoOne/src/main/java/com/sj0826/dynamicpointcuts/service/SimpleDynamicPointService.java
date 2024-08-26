package com.sj0826.dynamicpointcuts.service;

public class SimpleDynamicPointService 
{
	public void performTask()
	{
		System.out.println("Performing a task");
		internalTask();
	}
	
	public void internalTask()
	{
		System.out.println("Performing internal task");
	}
}
