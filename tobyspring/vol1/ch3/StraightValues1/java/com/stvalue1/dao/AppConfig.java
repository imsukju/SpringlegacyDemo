package com.stvalue1.dao;

import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

import com.mysql.jdbc.Driver;


@Configuration
public class AppConfig {

    @Bean
    public PropertySourcesPlaceholderConfigurer properties() {
        PropertySourcesPlaceholderConfigurer configurer = new PropertySourcesPlaceholderConfigurer();
        Properties properties = new Properties();
        properties.setProperty("jdbc.driver.className", "com.mysql.jdbc.Driver.class");
        properties.setProperty("jdbc.url", "jdbc:mysql://localhost:3306/mydb");
        configurer.setProperties(properties);
        return configurer;
    }
    
    @Value("${jdbc.driver.className}")
    private Class<? extends Driver> driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Bean(destroyMethod = "close")
    public DataSource myDataSource() {
    	SimpleDriverDataSource dataSource = new SimpleDriverDataSource();
        dataSource.setDriverClass(driverClassName);
        dataSource.setUrl(url);
        dataSource.setUsername("root");
        dataSource.setPassword("1234");
        return dataSource;
    }
    
    @Bean
    public UserDao setDao()
    {
		UserDao userDao = new UserDao();
		userDao.setDataSource(myDataSource());
		return userDao;
    }
}