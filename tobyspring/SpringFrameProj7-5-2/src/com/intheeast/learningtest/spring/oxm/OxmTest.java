package com.intheeast.learningtest.spring.oxm;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.List;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.oxm.Unmarshaller;
import org.springframework.oxm.XmlMappingException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.intheeast.springframe.dao.TestDaoFactory;
import com.intheeast.springframe.sqlservice.jaxb.SQLMapz;
import com.intheeast.springframe.sqlservice.jaxb.SQLQuery;


@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = {TestDaoFactory.class})
public class OxmTest {
	@Autowired
	Unmarshaller unmarshaller;
	
	@Test 
	public void unmarshallSqlMap() throws XmlMappingException, IOException  {
		Source xmlSource = new StreamSource(getClass().getResourceAsStream("sqlmap.json"));
		SQLMapz sqlmap = (SQLMapz)this.unmarshaller.unmarshal(xmlSource);
		
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
