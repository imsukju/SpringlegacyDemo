package com.intheeast.springframe.sqlservice;

import javax.annotation.PostConstruct;

import com.intheeast.springframe.sqlservice.jaxb.SQLMapReader;

public class BaseSqlService implements SqlService {

	private SQLMapReader squlmap = new SQLMapReader();
	private SqlReader sqlReader;
	private SqlRegistry sqlRegistry;
		
	public void setSqlReader(SqlReader sqlReader) {
		this.sqlReader = sqlReader;
	}

	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}

	@PostConstruct
	public void loadSql() {
		this.sqlReader.read(this.sqlRegistry);
	}

	public String getSql(String key) throws SqlRetrievalFailureException {
		try {
			return this.sqlRegistry.findSql(key);
		} 
		catch(SqlNotFoundException e) {
			throw new SqlRetrievalFailureException(e);
		}
	}

}
