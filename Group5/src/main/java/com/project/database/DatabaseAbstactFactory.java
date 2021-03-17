package com.project.database;

public abstract class DatabaseAbstactFactory {
	private static DatabaseAbstactFactory instance = null;
	
	public abstract IDatabaseUtilities createDatabaseUtilities();
	
	public static DatabaseAbstactFactory instance() {
		if (instance == null) {
			instance = new DatabaseConcreteFactory();
		}
		return instance;
	}

}
