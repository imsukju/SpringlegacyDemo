package com.intheeast.springframe.sqlservice;

import java.io.IOException;

import javax.annotation.PostConstruct;
import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.oxm.Unmarshaller;

import com.intheeast.springframe.dao.UserDao;
import com.intheeast.springframe.sqlservice.jaxb.SQLMapReader;
import com.intheeast.springframe.sqlservice.jaxb.SQLMapz;
import com.intheeast.springframe.sqlservice.jaxb.SQLQuery;
import com.intheeast.springframe.sqlservice.jaxb.SQLService;


public class OxmSqlService implements SqlService {

	private final BaseSqlService baseSqlService = new BaseSqlService();

	
	private final SQLService sqlservices;
	private final OxmSqlReader oxmSqlReader = new OxmSqlReader();
	private SqlRegistry sqlRegistry = new HashMapSqlRegistry();
	
	
	@Autowired
	public OxmSqlService(SQLService sqlservices)
	{
		this.sqlservices = sqlservices;
	}
	
	public void setSqlRegistry(SqlRegistry sqlRegistry) {
		this.sqlRegistry = sqlRegistry;
	}
	
	public void setUnmarshaller(Unmarshaller unmarshaller) {
		this.oxmSqlReader.setUnmarshaller(unmarshaller);
	}
	
	public void setSqlmap(Resource sqlmap) {
		this.oxmSqlReader.setSqlmap(sqlmap);
	}

	@PostConstruct
	public void loadSql() {
		this.baseSqlService.setSqlReader(this.oxmSqlReader);
		this.baseSqlService.setSqlRegistry(this.sqlRegistry);
		
		this.baseSqlService.loadSql();
	}

	public String getSql(String key) throws SqlRetrievalFailureException {
		return this.baseSqlService.getSql(key);
	}
	
	private class OxmSqlReader implements SqlReader {
		private Unmarshaller unmarshaller;
		private Resource sqlmap = new ClassPathResource("sqlmap.json");
		SQLMapReader sqlmapread = new SQLMapReader();
		public void setUnmarshaller(Unmarshaller unmarshaller) {
			this.unmarshaller = unmarshaller;
		}

		public void setSqlmap(Resource sqlmap) {
			this.sqlmap = sqlmap;
		}

		public void read(SqlRegistry sqlRegistry) {
			try {
				Source source = new StreamSource(sqlmap.getInputStream());
				SQLMapz sqlmap = (SQLMapz)this.unmarshaller.unmarshal(source);
				for(SQLQuery sql : sqlmap.getSqlmap()) {
					sqlRegistry.registerSql(sql.getKey(), sql.getQuery());
				}
			} catch (IOException e) {
				throw new IllegalArgumentException(this.sqlmap.getFilename() + "À» °¡Á®¿Ã ¼ö ¾ø½À´Ï´Ù", e);
			}
		}
	}

}
