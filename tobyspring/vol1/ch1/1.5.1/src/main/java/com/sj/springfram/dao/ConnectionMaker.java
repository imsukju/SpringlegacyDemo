package com.sj.springfram.dao;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionMaker {
	/*public abstract  인터페이스의 디폴트 에서스모디파이*/ 
	Connection makeConnection() throws ClassNotFoundException,
	SQLException;
}
