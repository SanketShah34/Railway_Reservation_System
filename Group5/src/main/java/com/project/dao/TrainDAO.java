package com.project.dao;

import java.util.List;

import com.project.entity.Train;

public interface TrainDAO {

	public List<Train> getAllTrain();

	public void saveTrain(Train train);

	public Train getTrain(Integer trainId);

	public void deleteTrain(Integer trainId);
	
}
