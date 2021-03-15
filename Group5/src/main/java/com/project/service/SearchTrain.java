package com.project.service;


import java.util.List;

import com.project.entity.Train;

public interface SearchTrain {
		
	public List<Train> searchTrains(com.project.entity.SearchTrain searchTrain);
}
