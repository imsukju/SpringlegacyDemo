package com.AspectjDemo.One.combinedpointcut.web;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

@Controller
public class WebController {
	public void hanldleRequest()
	{
		System.out.println("WebController hanldleRequest");
	}
}
