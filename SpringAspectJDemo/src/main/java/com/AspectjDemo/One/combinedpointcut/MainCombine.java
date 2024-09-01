package com.AspectjDemo.One.combinedpointcut;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.AspectjDemo.One.combinedpointcut.config.AppConfigCombine;
import com.AspectjDemo.One.combinedpointcut.dao.MydaoCombine;
import com.AspectjDemo.One.combinedpointcut.service.MyServiceCombie;
import com.AspectjDemo.One.combinedpointcut.web.WebController;

public class MainCombine {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfigCombine.class);
		
		WebController webcontroller = (WebController) context.getBean("webController");
		
		MyServiceCombie myService = (MyServiceCombie) context.getBean("myServiceCombie");

		MydaoCombine myDao = (MydaoCombine) context.getBean("mydaoCombine");
		
		webcontroller.hanldleRequest();
		myService.purfromBusinessLogic();
	}
	

}
