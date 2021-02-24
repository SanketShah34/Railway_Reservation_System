package com.project.service;

import java.sql.Connection;

public interface DButilities {
	public Connection EstConnection();
	
	public void closeConnection(Connection conn);
}
