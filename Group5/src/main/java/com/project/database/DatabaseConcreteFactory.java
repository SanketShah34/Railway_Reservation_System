package com.project.database;

public class DatabaseConcreteFactory extends DatabaseAbstactFactory{

	@Override
	public IDatabaseUtilities createDatabaseUtilities() {
		 return new DatabaseUtilities();
	}

}
