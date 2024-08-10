package com.sjub.ioc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.springframework.beans.factory.FactoryBean;

public class CustomObjectFactoryBean implements FactoryBean<CustomObject> {
	
    @Override
    public CustomObject getObject() throws Exception {
        return new CustomObject("FactoryBean Example");
    }

	@Override
	public Class<?> getObjectType() {
		// TODO Auto-generated method stub
		return CustomObject.class;
	}
	
	@Override
	public boolean isSingleton()
	{
		return true;
	}


	
	
}
