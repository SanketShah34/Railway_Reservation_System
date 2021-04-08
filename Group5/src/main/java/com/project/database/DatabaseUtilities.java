package com.project.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import org.springframework.stereotype.Component;

@Component
public class DatabaseUtilities implements IDatabaseUtilities {
    private String DRIVER = "com.mysql.cj.jdbc.Driver";
	public Connection connection;
	
	@Override
	public Connection  establishConnection(){
		try {
			Class.forName(DRIVER);
		    String url = System.getenv("URL");
			String userName = System.getenv("USERNAME");
			String password = System.getenv("PASSWORD");
			
		    connection = DriverManager.getConnection(url , userName, password);
			return connection;
		} catch (Exception exception) {
			exception.printStackTrace();
			return null;
		}
	}

	@Override
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
	@Override
	public void closeStatement(CallableStatement statement) {
		try {
			statement.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Override
	public void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	
}
