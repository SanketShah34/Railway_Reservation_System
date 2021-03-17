package com.project.database;

public class DatabaseConcrete extends DatabaseAbstactFactory{
	private IDatabaseUtilities databaseUtilities;
	
	public IDatabaseUtilities createDatabaseUtilities() {
		if (databaseUtilities == null) {
			databaseUtilities = new DatabaseUtilities();
		}
		return databaseUtilities;
	}
	
	public IDatabaseUtilities createNewDatabaseUtilities() {
		return new DatabaseUtilities();
	}

}
