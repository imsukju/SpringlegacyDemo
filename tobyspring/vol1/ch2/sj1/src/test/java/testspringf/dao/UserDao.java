package testspringf.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

import javax.sql.DataSource;

import org.springframework.dao.EmptyResultDataAccessException;

import testspringf.domain.User;

public class UserDao {
	private DataSource dataSource;
	private String table_name;
	private String cloumname;
	private Scanner scanner = new Scanner(System.in);
	private String choice;

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

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
		choice = scanner.nextLine();
		ps.setString(1, choice);
		ps.setString(2, "bu%");
		
		ps.executeUpdate();

		ps.close();
		c.close();
		return choice;
	}
	
	public void deletecolumn() throws SQLException
	{
		Connection c = dataSource.getConnection();
		PreparedStatement ps = c.prepareStatement("update " + this.table_name+ " set name = ? where id like bu_");
	}
}
