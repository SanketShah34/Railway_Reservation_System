package com.project.database;

import java.sql.Connection;
import java.sql.DriverManager;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class DButilitiesimpl implements DButilities {

	@Value("${db.driver}")
	private String DRIVER;

	@Value("${db.url}")
	private String URL;

	@Value("${db.username}")
	private String USERNAME;

	@Value("${db.password}")
	private String PASSWORD;

	@Override
	public Connection estConnection() {
		System.out.println("----");
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			java.sql.Connection com = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		//	System.out.println("com object " + com);
			return com;
		} catch (Exception e) {
		//	System.out.println("exception:=" + e);
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public void closeConnection(Connection con) {
		try {
			con.close();
		} catch (Exception e) {
		//	System.out.println("exception:=" + e);
			e.printStackTrace();
		}
	}

}
