package com.sj0826.dynamicpointcuts;

import org.springframework.aop.support.ControlFlowPointcut;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.sj0826.dynamicpointcuts.caller.TaskCaller;
import com.sj0826.dynamicpointcuts.config.AppDynamicPointutConfig;
import com.sj0826.dynamicpointcuts.service.SimpleDynamicPointService;

public class Main {

	public static void main(String[] args) {
		AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppDynamicPointutConfig.class);
		
		
		//ControlFlowPointcut(TaskCaller.class, "taskCaller");
		TaskCaller caller = (TaskCaller) context.getBean("taskCaller");
		caller.callTask();

		context.getBean("proxyFB", SimpleDynamicPointService.class).performTask();
	}

}
