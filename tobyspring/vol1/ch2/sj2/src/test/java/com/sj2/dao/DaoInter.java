package com.sj2.dao;


import javax.sql.DataSource;



public interface DaoInter<K extends DataSource ,V extends UserDao> {


	public K dataSource();

	public V userDao();
}
