package com.sj624.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sj624.domain.Level;
import com.sj624.domain.User;





public class UserDaoJdbc implements UserDao {
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);
	}
	
	private JdbcTemplate jdbcTemplate;
	
	private RowMapper<User> userMapper = 
		new RowMapper<User>() {
				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getString("id"));
				user.setName(rs.getString("name"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setLevel(Level.valueOf(rs.getInt("level")));
				user.setLogin(rs.getInt("login"));
				user.setRecommend(rs.getInt("recommend"));
				return user;
			}
		};

	public void add(User user) {
		this.jdbcTemplate.update(
				"insert into users(id, name, password, email, level, login, recommend) " +
				"values(?,?,?,?,?,?,?)", 
					user.getId(),
					user.getName(), 
					user.getPassword(), 
					user.getEmail(), 
					user.getLevel().intValue(), 
					user.getLogin(), 
					user.getRecommend());
	}

//	public User get(String id) {
//		return this.jdbcTemplate.queryForObject("select * from users where id = ?",
//				new Object[] {id}, this.userMapper);
//	} 
	
	public Optional<User> get(String id) {
		
		String sql = "select * from users where id = ?";
		
		try(Stream<User> stream =
				jdbcTemplate.queryForStream(sql, userMapper,id)){
			return stream.findFirst();
		}
		catch (DataAccessException e)
		{
			return Optional.empty();
		} 
	}

	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from users",int.class);
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id",this.userMapper);
	}

	public void update(User user) {
		this.jdbcTemplate.update(
				"update users set name = ?, password = ?, email = ?, level = ?, login = ?, " +
				"recommend = ? where id = ? ", user.getName(), user.getPassword(), user.getEmail(), 
		user.getLevel().intValue(), user.getLogin(), user.getRecommend(),
		user.getId());
		
	}

	@Override
	public DataSource getdatasource() {
		// TODO Auto-generated method stub
		return this.jdbcTemplate.getDataSource();
	}
}

