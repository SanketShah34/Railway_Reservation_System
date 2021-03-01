package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.TrainDAO;
import com.project.entity.Train;

@Service
public class TrainServiceImpl implements TrainService{
	
	@Autowired
	TrainDAO trainDAO;

	public List<Train> ListOfTrains() {
		return trainDAO.getAllTrain();
	}

	public boolean saveTrain(Train train) {
		return trainDAO.saveTrain(train);
	}

	public Train getTrain(Integer trainId) {
		return trainDAO.getTrain(trainId);
	}

	public void deleteTrain(Integer trainId) {
		 trainDAO.deleteTrain(trainId);
	}
}