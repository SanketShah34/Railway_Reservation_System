package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.setup.IRouteDAO;
import com.project.dao.SearchTrainDAO;
import com.project.setup.Train;

@Service
public class SearchTrainImpl implements SearchTrain {

	@Autowired
	SearchTrainDAO searchTrainDAO;
	
	@Override
	public List<Train> searchTrains(com.project.entity.SearchTrain searchTrain) {
		
		return searchTrainDAO.searchTrains(searchTrain);
	}

}
