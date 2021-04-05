package com.project.database;

public abstract class DatabaseAbstactFactory {
	
	private static DatabaseAbstactFactory instance = null;
	public abstract IDatabaseUtilities createDatabaseUtilities();
	
	public static DatabaseAbstactFactory instance() {
		if (null ==  instance) {
			instance = new DatabaseConcreteFactory();
		}
		return instance;
	}

}
