package com.project.logic;

import java.util.List;

import com.project.setup.Train;

public interface findFare {

	public List<Train> findFareofTrainjourney(List<Train> trains , String sourceStation , String destinationStation);
	
	public double calculateFareByDistance(int distance, double fare);
	
	public double calculateFareByTrainType(int distance, String trainType) throws Exception;
	
	public double calculateFareByAge(double fare, int age);
	
	
}
