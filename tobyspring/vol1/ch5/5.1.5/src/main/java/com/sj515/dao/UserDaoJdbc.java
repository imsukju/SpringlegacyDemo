package com.sj515.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.sj515.domain.Level;
import com.sj515.domain.User;



public class UserDaoJdbc implements UserDao{
	
	private JdbcTemplate jdbcTemplate;
	
	public void setDataSource(DataSource dataSource) {
		this.jdbcTemplate = new JdbcTemplate(dataSource);

	}
	
	
//	private RowMapper<User> RowuserMapper = 
//		new RowMapper<User>() {
//				public User mapRow(ResultSet rs, int rowNum) throws SQLException {
//				User user = new User();
//				user.setId(rs.getString("id"));
//				user.setName(rs.getString("name"));
//				user.setPassword(rs.getString("password"));
//				user.setLevel(Level.valueOf(rs.getInt("level")));
//				user.setLogin(rs.getInt("login"));
//				user.setRecommend(rs.getInt("recommend"));
//				return user;
//			}
//		};

	
	private RowMapper<User> RowuserMapper =(rs, nun) ->{
		User user = new User();
	    user.setId(rs.getString("id"));
	    user.setName(rs.getString("name"));
	    user.setPassword(rs.getString("password"));
	    user.setLevel(Level.valueOf(rs.getInt("level")));
	    user.setLogin(rs.getInt("login"));
	    user.setRecommend(rs.getInt("recommend"));
	    return user;
	};
	

//	this.level = level;
//	this.login = login;
//	this.recommend = recommend;
	public void add(final User user) {
		this.jdbcTemplate.update("insert into users(id, name, password,level ,login ,recommend) values(?,?,?,?,?,?)",
				user.getId()
				,user.getName()
				,user.getPassword()
				,user.getLevel().intValue()
				,user.getLogin()
				,user.getRecommend()
				);
		
	}

	public Optional<User> get(String id) {
		
		String sql = "select * from users where id = ?";
		
		try(Stream<User> stream =
				jdbcTemplate.queryForStream(sql, RowuserMapper,id)){
			return stream.findFirst();
		}
		catch (DataAccessException e)
		{
			return Optional.empty();
		} 
	}
//		return this.jdbcTemplate.queryForObject("select * from users where id = ?",
//				new Object[] {id}, this.userMapper);
	

	public void deleteAll() {
		this.jdbcTemplate.update("delete from users");
	}

	public int getCount() {
		return this.jdbcTemplate.queryForObject("select count(*) from users",int.class);
	}

	public List<User> getAll() {
		return this.jdbcTemplate.query("select * from users order by id",this.RowuserMapper);
	}
	
	@Override
	public void update(User user) {
		this.jdbcTemplate.update(
				"update users set name = ?, password = ?, level = ?, login = ?, " +
				"recommend = ? where id = ? ", user.getName(), user.getPassword(), 
		user.getLevel().intValue(), user.getLogin(), user.getRecommend(),
		user.getId());
		
	}
}
