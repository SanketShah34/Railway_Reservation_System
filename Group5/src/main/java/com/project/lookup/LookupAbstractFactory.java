package com.project.lookup;

public abstract class LookupAbstractFactory {
	
	private static  LookupAbstractFactory instance = null;
	
	public abstract ISearchTrainDAO createSearchTrainDAO();
	public abstract ISearchTrainDAO createNewSearchTrainDAO();
	public abstract ISearchTrain createSearchTrain();
	public abstract ISearchTrain createNewSearchTrain();
	
	public static LookupAbstractFactory instance() {
		if (null == instance) {
			instance = new LookupConcreteFactory();
		}
		return instance;
	}
	

}
