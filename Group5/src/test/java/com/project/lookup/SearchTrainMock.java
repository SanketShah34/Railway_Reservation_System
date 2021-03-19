package com.project.lookup;

import java.util.Date;

public class SearchTrainMock {
	
	public ISearchTrain createSearchTrainMock(ISearchTrain searchTrain) {
		searchTrain.setSourceStation("1");
		searchTrain.setDestinationStation("4");
		searchTrain.setDateofJourny(new Date());
		searchTrain.setTrainType("AC SLEEPER");
		return searchTrain;
	}

}
