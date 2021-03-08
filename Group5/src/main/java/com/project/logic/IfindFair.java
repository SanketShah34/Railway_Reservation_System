package com.project.logic;

import java.util.List;

import com.project.entity.Train;

public interface IfindFair {

	public List<Train> findFairofTrainjourney(List<Train> trains , String sourceStation , String destinationStation);
		
	
}
