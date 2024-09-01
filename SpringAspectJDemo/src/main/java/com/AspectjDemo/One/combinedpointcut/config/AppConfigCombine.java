package com.AspectjDemo.One.combinedpointcut.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.AspectjDemo.One.combinedpointcut")
public class AppConfigCombine {

}
