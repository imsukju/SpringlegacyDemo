package com.AspectjDemo.One.DeclaringAdvice.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan(basePackages = "com.AspectjDemo.One.DeclaringAdvice")
public class AppConfigDeclaringAdvice {

}
