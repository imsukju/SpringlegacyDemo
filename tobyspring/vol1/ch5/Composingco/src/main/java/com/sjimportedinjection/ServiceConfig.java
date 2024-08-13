package com.sjimportedinjection;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {
	
	AccountRepository accountRepository; // 자동 주입 테스트
	@Bean
	public TransferService transferService(/* AccountRepository accountRepository */) {
		System.out.println("+ServiceConfig::transferService");
		TransferServiceImpl tsil = new TransferServiceImpl(accountRepository);
		System.out.println("-ServiceConfig::transferService");

		return tsil;
    }

}

