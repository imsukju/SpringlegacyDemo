package com.sjcompos;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(value = ConfigA.class)
public class ConfigB{

	@Bean
	public B b() {
		return new B();
	}
}