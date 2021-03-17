package com.project.database;

import java.sql.Connection;

public interface DButilities {
	
	public Connection estConnection();
	
	public void closeConnection(Connection conn);
}
