package com.intheeast.springframe.sqlservice;

import java.io.InputStream;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.intheeast.springframe.dao.UserDao;
import com.intheeast.springframe.sqlservice.jaxb.SQLMapz;
import com.intheeast.springframe.sqlservice.jaxb.SQLQuery;


public class JaxbXmlSqlReader implements SqlReader {

	private final String DEFAULT_SQLMAP_FILE = "sqlmap.xml";
	private String sqlmapFile = DEFAULT_SQLMAP_FILE;

	public void setSqlmapFile(String sqlmapFile) { this.sqlmapFile = sqlmapFile; }

	public void read(SqlRegistry sqlRegistry) {
		String contextPath = SQLMapz.class.getPackage().getName(); 
		try {
			JAXBContext context = JAXBContext.newInstance(contextPath);
			Unmarshaller unmarshaller = context.createUnmarshaller();
			InputStream is = UserDao.class.getResourceAsStream(sqlmapFile);
			SQLMapz sqlmap = (SQLMapz)unmarshaller.unmarshal(is);
			for(SQLQuery sql : sqlmap.getSqlmap()) {
				sqlRegistry.registerSql(sql.getKey(), sql.getQuery());
			}
		} catch (JAXBException e) { throw new RuntimeException(e); } 		
	}

}
