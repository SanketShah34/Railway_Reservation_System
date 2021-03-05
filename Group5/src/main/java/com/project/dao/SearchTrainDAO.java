package com.project.dao;

import java.util.List;

import com.project.entity.SearchTrain;
import com.project.entity.Train;

public interface SearchTrainDAO {

	public List<Train> searchTrains(SearchTrain searchTrain);
	
}
