package com.sj.springfram.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class HConnectionMaker implements ConnectionMaker {

	@Override
	public Connection makeConnection() throws ClassNotFoundException, SQLException {
		Connection c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
				"sa",
				"1234");
		return c;
	}

}
