package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TrainDAO;
import com.project.entity.Train;
import com.project.service.TrainService;

@Service
public class TrainServiceImpl implements TrainService{
	
	@Autowired
	TrainDAO trainDAO;

	public List<Train> ListOfTrains() {
		return trainDAO.getAllTrain();
	}

	public void saveTrain(Train train) {
		trainDAO.saveTrain(train);
	}

	public Train getTrain(Integer trainId) {
		return trainDAO.getTrain(trainId);
	}

	public void deleteTrain(Integer trainId) {
		 trainDAO.deleteTrain(trainId);
	}
}