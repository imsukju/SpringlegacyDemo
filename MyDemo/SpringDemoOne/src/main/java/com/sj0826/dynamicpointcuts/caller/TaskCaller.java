package com.sj0826.dynamicpointcuts.caller;

import com.sj0826.dynamicpointcuts.service.SimpleDynamicPointService;

public class TaskCaller {
	private final SimpleDynamicPointService simpleservice;
	
	public TaskCaller(SimpleDynamicPointService simpleservice)
	{
		this.simpleservice = simpleservice;  
	}
	
	public void callTask()
	{
		System.out.println("Calling task from TaskCaller");
		simpleservice.performTask();
	}

	public SimpleDynamicPointService getSimpleservice() {
		return simpleservice;
	}
	
}
