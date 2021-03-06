package com.project.logic;

import java.util.List;

import com.project.entity.Train;

public interface findFare {

	public List<Train> findFareofTrainjourney(List<Train> trains , String sourceStation , String destinationStation);
	
	
	
}
