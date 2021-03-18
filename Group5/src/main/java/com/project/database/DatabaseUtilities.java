package com.project.database;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
public class DatabaseUtilities implements IDatabaseUtilities {
	
    private String DRIVER = "com.mysql.cj.jdbc.Driver";
	private String URL = "jdbc:mysql://db-5308.cs.dal.ca:3306/CSCI5308_5_DEVINT?useSSL=false&serverTimezone=UTC";
	private String USERNAME = "CSCI5308_5_DEVINT_USER";
	private String PASSWORD = "CBfHk3FuJet8gKvT";
	public Connection connection;

	
	@Override
	public Connection  establishConnection(){
		System.out.println(" hello");
		try {
			Class.forName(DRIVER);
		    connection = DriverManager.getConnection(URL , USERNAME, PASSWORD);
			return connection;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void closeConnection(Connection conn) {
		try {
			conn.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void closeStatement(CallableStatement statement) {
		try {
			statement.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void closeResultSet(ResultSet resultSet) {
		try {
			resultSet.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
