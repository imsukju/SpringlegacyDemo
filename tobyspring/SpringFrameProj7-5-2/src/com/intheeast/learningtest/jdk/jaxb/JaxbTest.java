package com.intheeast.learningtest.jdk.jaxb;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.junit.jupiter.api.Test;
import org.springframework.core.io.ClassPathResource;

import com.intheeast.springframe.sqlservice.jaxb.SQLMapz;
import com.intheeast.springframe.sqlservice.jaxb.SQLQuery;



public class JaxbTest {
	@Test
	public void readSqlmap() throws JAXBException, IOException {
		
		String contextPath = SQLMapz.class.getPackage().getName(); 
		JAXBContext context = JAXBContext.newInstance(contextPath);
		Unmarshaller unmarshaller = context.createUnmarshaller();
		SQLMapz sqlmap = (SQLMapz) unmarshaller.unmarshal(
				getClass().getResourceAsStream("sqlmap.xml"));
		
		List<SQLQuery> sqlList = sqlmap.getSqlmap();

		assertEquals(sqlList.size(), 3);
		assertEquals(sqlList.get(0).getKey(), "add");
		assertEquals(sqlList.get(0).getQuery(), "insert");
		assertEquals(sqlList.get(1).getKey(), "get");
		assertEquals(sqlList.get(1).getQuery(), "select");
		assertEquals(sqlList.get(2).getKey(), "delete");
		assertEquals(sqlList.get(2).getQuery(), "delete");
	}

}
