package com.project.lookup;

import java.util.Date;

public interface ISearchTrain {

	String getSourceStation();

	void setSourceStation(String sourceStation);

	String getDestinationStation();

	void setDestinationStation(String destinationStation);

	Date getDateofJourny();

	void setDateofJourny(Date dateofJourny);

	String getTrainType();

	void setTrainType(String trainType);

	boolean issourceStationAndDestinationStationSame(String sourceStation, String destinationStation);

	boolean isDatePreviousDate(Date dateofJourny);
	
	public boolean isDateInWithinOneMonthPeriod(Date date);

}
