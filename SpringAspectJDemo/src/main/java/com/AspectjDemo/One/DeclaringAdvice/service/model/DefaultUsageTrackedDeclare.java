package com.AspectjDemo.One.DeclaringAdvice.service.model;

import org.springframework.beans.factory.annotation.Qualifier;


public class DefaultUsageTrackedDeclare implements UsageTrackedDeclare {

	private int useCount = 0;
	@Override
	public void incrementUsage() {
		// TODO Auto-generated method stub
		useCount++;
	}

	@Override
	public int getUseCount() {
		// TODO Auto-generated method stub
		return this.useCount;
	}

}
