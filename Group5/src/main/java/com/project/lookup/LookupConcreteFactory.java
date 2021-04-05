package com.project.lookup;

public class LookupConcreteFactory extends LookupAbstractFactory {

	private ISearchTrainDAO searchTrainDAO;
	private ISearchTrain searchtrain;

	public ISearchTrainDAO createSearchTrainDAO() {
		if (searchTrainDAO == null) {
			searchTrainDAO = new SearchTrainDAO();
		}
		return searchTrainDAO;
	}

	public ISearchTrainDAO createNewSearchTrainDAO() {
		return new SearchTrainDAO();
	}

	public ISearchTrain createSearchTrain() {
		if (searchtrain == null) {
			searchtrain = new SearchTrain();
		}
		return searchtrain;
	}

	public ISearchTrain createNewSearchTrain() {
		return new SearchTrain();
	}

}
