package com.project.service;


import java.util.List;
import com.project.setup.Train;

public interface SearchTrain {
		
	public List<Train> searchTrains(com.project.entity.SearchTrain searchTrain);
}
