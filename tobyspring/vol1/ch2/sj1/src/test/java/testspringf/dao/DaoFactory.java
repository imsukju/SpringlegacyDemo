package testspringf.dao;

import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SimpleDriverDataSource;

//ioc 에 전달하는 컨피규레이선 메타데이터
@Configuration
public class DaoFactory {
	@Bean
	public DataSource dataSource() {
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();

		dataSource.setDriverClass(com.mysql.jdbc.Driver.class);
		dataSource.setUrl("jdbc:mysql://localhost/sbdt_db?characterEncoding=UTF-8");
		dataSource.setUsername("root");
		dataSource.setPassword("1234");

		return dataSource;
	}
	
	@Bean
	public DataSource HdataSource()
	{
		SimpleDriverDataSource dataSource = new SimpleDriverDataSource ();
		dataSource.setDriverClass(org.h2.Driver.class);
		dataSource.setUrl("jdbc:h2:tcp://localhost/~/test");
		dataSource.setUsername("sa");
		dataSource.setPassword("1234");		
		
		return dataSource;
	}

	@Bean
	public UserDao userDao() {
		Scanner sc = new Scanner(System.in);
		
		UserDao userDao = new UserDao();
		
		System.out.println("myssql or h2 선택");
		String choice = sc.nextLine();
		if (choice.equals("mysql"))
		{
			userDao.setDataSource(dataSource());
		}
		else if (choice.equals("h2"))
		{
			userDao.setDataSource(HdataSource());
		}
		return userDao;
	}
}
