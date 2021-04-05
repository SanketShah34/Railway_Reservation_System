package com.project.calculation;

import java.sql.Date;
import java.util.List;
import com.project.lookup.ISearchTrain;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;

public interface ITrainFilterAndCalculation {

	public List<ITrain> filterTrain(List<ITrain> trains, ISearchTrain searchTrain, IRouteDAO routeDAO);

	public boolean countPickUpAndDropUpTime(double timeAtTrainStartItsJourneyInMinutes,
			double timeRequiredByTrainToReachSourceStation, double timeRequiredByTrainForDestinationStation,
			ITrain train, ISearchTrain searchTrain);

	public void calculateStartDateOfTrain(ITrain train, ISearchTrain searchTrain,
			double timeRequiredByTrainToReachSourceStation, double trainStarttime);

	public String minuteToHoursConverter(double minutes);

	public String minuteFormater(double number);

	public boolean checkWhetherTrainIsAvailableOrNotOnThatDay(ITrain train, int daytoIncrement,
			ISearchTrain searchTrain);

	public double hoursToMinuteConverter(String time);

	public void setStartDateForTrain(ITrain train, int dayToRemove, ISearchTrain searchTrain);

	public void SetDateForDropUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain);

	public void SetDateForPickUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain);

	public String getDaysNameFromDate(Date dateToBeformate);

	public double calculateFareByDistance(double distance, double fare);

	public double calculateFareByTrainType(double distance, String trainType) throws Exception;

	public double calculateFareByAge(double fare, int age);

}
