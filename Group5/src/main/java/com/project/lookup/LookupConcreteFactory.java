package com.project.lookup;

public class LookupConcreteFactory extends LookupAbstractFactory{
	
	private ISearchTrainDAO searchTrainDAO;
	private ISearchTrain searchtrain;

	@Override
	public ISearchTrainDAO createSearchTrainDAO() {
		if (searchTrainDAO == null) {
			searchTrainDAO = new SearchTrainDAO();
    	}
    	return searchTrainDAO;	
	}

	@Override
	public ISearchTrainDAO createNewSearchTrainDAO() {
		return new SearchTrainDAO();
	}

	@Override
	public ISearchTrain createSearchTrain() {
		if (searchtrain == null) {
			searchtrain = new SearchTrain();
    	}
    	return searchtrain;
	}

	@Override
	public ISearchTrain createNewSearchTrain() {
		return new SearchTrain();
	}


}
