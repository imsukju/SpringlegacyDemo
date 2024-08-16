package com.postconstruck;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class AppConfig {

    private final TransferService transferService;

    // 매개변수 기반 주입을 사용하여 TransferService를 초기화
    public AppConfig(TransferService transferService) {
        this.transferService = transferService;
    }

    @Bean
    public TransferService transferService(AccountRepository accountRepository) {
        return new TransferServiceImpl(accountRepository);
    }

    @Bean
    public AccountRepository accountRepository(DataSource dataSource) {
        return new JdbcAccountRepository(dataSource);
    }

    @Bean
    public DataSource dataSource() {
		return null;
        // 데이터베이스 연결을 위한 DataSource 설정
        // 예를 들어, HikariDataSource 또는 다른 구현체를 반환
    }

    @PostConstruct
    public void init() {
        // 이 메서드 내에서 동일한 구성 클래스의 비정적 @Bean 메서드에 접근하지 마세요
        // 예: transferService(); (비정적 메서드 접근 금지)
        System.out.println("AppConfig initialized");
    }
}
