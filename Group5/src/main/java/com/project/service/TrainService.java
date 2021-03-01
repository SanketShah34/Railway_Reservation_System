package com.project.service;

import java.util.List;

import com.project.entity.Train;

public interface TrainService {

	public List<Train> listOfTrains();

	public boolean saveTrain(Train train);

	public Train getTrain(Integer trainId);

	public void deleteTrain(Integer trainId);

}
