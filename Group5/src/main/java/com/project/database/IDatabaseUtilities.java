package com.project.database;

import java.sql.Connection;

public interface IDatabaseUtilities {

	public Connection establishConnection();

	public void closeConnection(Connection conn);

}
