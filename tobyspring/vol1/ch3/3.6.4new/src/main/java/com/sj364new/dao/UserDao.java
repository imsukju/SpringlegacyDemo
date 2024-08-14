package com.sj364new.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowMapper;

import com.sj364new.domain.User;

public class UserDao {
	private DataSource dataSource;
		
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
			
		this.dataSource = dataSource;
	}
	
	private JdbcTemplate jdbcTemplate;
	
	public void add(final User user) {
		this.jdbcTemplate.update("insert into users(id, name, password) values(?,?,?)",
				user.getId()
				,user.getName()
				,user.getPassword()
				);
	}


	public User get(String id) {
		return this.jdbcTemplate.queryForObject("select * from users where id = ?",
				new Object[] {id}, 
				new RowMapper<User>() {
					public User mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						User user = new User();
						user.setId(rs.getString("id"));
						user.setName(rs.getString("name"));
						user.setPassword(rs.getString("password"));
						return user;
					}
				});
	} 


	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		List<Integer> result = jdbcTemplate.query("select count(*) from users", 
				(rs, rsnum)-> rs.getInt(1)
				);
		return DataAccessUtils.singleResult(result);
	}

	//public <T> T query(final String sql, final ResultSetExtractor<T> rse)
	//extractData(ResultSet rs) throws SQLException, DataAccessException; 

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id",
				(rs, rsnum)->{
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				return user;
				}
				);
		
		
	}
	
//	public List<User> getAll() {
//		return this.jdbcTemplate.query("select * from users order by id",
//				new RowMapper<User>() {
//					public User mapRow(ResultSet rs, int rowNum)
//							throws SQLException {
//						User user = new User();
//						user.setId(rs.getString("id"));
//						user.setName(rs.getString("name"));
//						user.setPassword(rs.getString("password"));
//						return user;
//					}
//				});
//	}

}
