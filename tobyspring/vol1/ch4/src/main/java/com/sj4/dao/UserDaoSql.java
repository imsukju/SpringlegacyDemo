package com.sj4.dao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.sj4.domain.User;

public class UserDaoSql {
	
	public UserDaoSql()
	{
		
	}
	
	private Connection getConnection() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdcb.Drivers");
		
		Connection c = DriverManager.getConnection("jdbc:myssql://localhos:3306/sbdt_db?characterEncoding=UTF-8",
				"root"
				,"1234");
		return c;
	}
	
	public void add(User user) throws SQLException, ClassNotFoundException {
		Connection c = getConnection();
		PreparedStatement ps = c.prepareStatement("insert into users(id,name,password) values(?,?,?)");
		
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());
		
		ps.execute();
		c.close();
		ps.close();
		
		
	}
}
