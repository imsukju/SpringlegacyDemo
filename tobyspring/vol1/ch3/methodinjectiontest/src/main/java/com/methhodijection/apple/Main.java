package com.methhodijection.apple;

import java.util.Map;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {

	 // Main method to run the example
	 public static void main(String[] args) {
	     ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
	     CommandManager commandManager = context.getBean(CommandManager.class);

	     // Creating a sample state map
	     Map<String, Object> state = Map.of("key1", "value1", "key2", "value2");

	     // Processing the command
	     Object result = commandManager.process(state);
	     System.out.println(result);
	 }
	
}
