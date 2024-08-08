package com.sj2.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import com.sj2.domain.User;

public class UserDao {
	private DataSource dataSource;
	private String table_name = "users";
	private String cloumname = "id";
	private Scanner scanner = new Scanner(System.in);
	private int countcreat = 0;
	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

//	public int creattable() throws SQLException
//	{
//		Connection c = this.dataSource.getConnection();
//		if(countcreat == 0)
//		{
//			PreparedStatement ps = c.prepareStatement(
//					"create table users(id varchar(10) prmarykey,name varchar(20) not null,"
//					+ "	password varchar(10) not null)");
//			countcreat += 1;
//			ps.executeUpdate();
//
//			ps.close();
//			c.close();
//		}
//		else
//		{
//			return countcreat;
//		}
//
//		return countcreat;
//	}
	public void add(User user) throws SQLException {
		Connection c = this.dataSource.getConnection();

		PreparedStatement ps = c.prepareStatement(
			"insert into users(id, name, password) values(?,?,?)");
		ps.setString(1, user.getId());
		ps.setString(2, user.getName());
		ps.setString(3, user.getPassword());

		ps.executeUpdate();

		ps.close();
		c.close();
	}

	public User get(String id) throws SQLException {
		Connection c = this.dataSource.getConnection();
		PreparedStatement ps = c
				.prepareStatement("select * from users where id = ?");
		ps.setString(1, id);
		
		ResultSet rs = ps.executeQuery();

		User user = null;
		if (rs.next()) {
			user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));
		}

		rs.close();
		ps.close();
		c.close();
		
		if (user == null) throw new EmptyResultDataAccessException(1);

		return user;
	}

	public void deleteAll() throws SQLException {
		Connection c = dataSource.getConnection();
	
		PreparedStatement ps = c.prepareStatement("delete from users");
		ps.executeUpdate();

		ps.close();
		c.close();
	}	

	public int getCount() throws SQLException  {
		Connection c = dataSource.getConnection();
	
		PreparedStatement ps = c.prepareStatement("select count(*) from users");

		ResultSet rs = ps.executeQuery();
		rs.next();
		int count = rs.getInt(1);

		rs.close();
		ps.close();
		c.close();
	
		return count;
	}
	
	public String updateis(String table, String column) throws SQLException
	{
		Connection c = dataSource.getConnection();
		this.table_name = table;
		this.cloumname = column;
		PreparedStatement ps = c.prepareStatement("update " + this.table_name+ " set name = ? where "+ this.cloumname+" like ?");
		
		System.out.println("바꿀 이름을 선택");
		String choice;
		choice = scanner.nextLine();
		ps.setString(1, choice);
		ps.setString(2, "bu%");
		
		ps.executeUpdate();

		ps.close();
		c.close();
		return choice;
	}
	
	public void deletecolumn(String table, String column) throws SQLException
	{
		Connection c = dataSource.getConnection();
		String query = "DELETE FROM " + table + " WHERE " + column + " LIKE ?";
		//"delete from "+table+"where "+column+" like ?;";
		PreparedStatement ps = c.prepareStatement(query);
		ps.setString(1,"bu%");
		
		ps.executeUpdate();

		ps.close();
		c.close();
		System.out.println("완료");
	}
}
