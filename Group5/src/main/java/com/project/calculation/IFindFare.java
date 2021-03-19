package com.project.calculation;

import java.util.List;
import com.project.setup.ITrain;

public interface IFindFare {

	public List<ITrain> findFareofTrainJourney(List<ITrain> trains , String sourceStation , String destinationStation);
	
	public double calculateFareByDistance(int distance, double fare);
	
	public double calculateFareByTrainType(int distance, String trainType) throws Exception;
	
	public double calculateFareByAge(double fare, int age);
	
	public int timeCounter(String time);
	
	public String minuteToHoursConverter(int minutes);
	
	public String minuteFormater(int minutes);
	
}
