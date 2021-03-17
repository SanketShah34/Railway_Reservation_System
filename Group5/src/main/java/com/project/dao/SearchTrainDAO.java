package com.project.dao;

import java.util.List;

import com.project.entity.SearchTrain;
import com.project.setup.Train;

public interface SearchTrainDAO {

	public List<Train> searchTrains(SearchTrain searchTrain);
	
}
