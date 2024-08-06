	package com.sj.springframe.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.sj.springframe.domain.User;

public class UserDao {
		public void add(User user) throws ClassNotFoundException, SQLException {
			Connection c = getConnectionH2();

			PreparedStatement ps = c.prepareStatement(
				"insert into users(id, name, password) values(?,?,?)");
			ps.setString(1, user.getId());
			ps.setString(2, user.getName());
			ps.setString(3, user.getPassword());

			int box = ps.executeUpdate();
			
			
			System.out.println(box);

			ps.close();
			c.close();
		}


		public User get(String id) throws ClassNotFoundException, SQLException {
			Connection c = getConnectionH2();
			PreparedStatement ps = c
					.prepareStatement("select * from users where id = ?");
			ps.setString(1, id);

			ResultSet rs = ps.executeQuery();
			rs.next();
			User user = new User();
			user.setId(rs.getString("id"));
			user.setName(rs.getString("name"));
			user.setPassword(rs.getString("password"));

			rs.close();
			ps.close();
			c.close();

			return user;
		}

		//리펙토링이 추가됨.. 중복된 driver 클래스 로드
		//중복된 데이터 베이스 연결 코드를 하나의 메서도로 통합
		private Connection getConnection() throws ClassNotFoundException,SQLException {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost/sbdt_db?characterEncoding=UTF-8", 
					"root",
					"1234");
			return c;
		}
		

		
		private Connection getConnectionH2() throws ClassNotFoundException,
		SQLException {
			
			Class.forName("org.h2.Driver");
			// jdbc:h2:tcp://localhost/~/test
			Connection c = DriverManager.getConnection("jdbc:h2:tcp://localhost/~/test",
					"sa",
					"1234");
	
			return c;
		}		

		public static void main(String[] args) throws ClassNotFoundException, SQLException {
			UserDao dao = new UserDao();
			

			User user = new User();
			user.setId("Hello421");
			user.setName("World2");
			user.setPassword("married");

			dao.add(user);
				
			System.out.println(user.getId() + " 등록 성공");
			
			User user2 = dao.get(user.getId());
			System.out.println(user2.getName());
			System.out.println(user2.getPassword());
				
			System.out.println(user2.getId() + " 조회 성공");
		}
}
