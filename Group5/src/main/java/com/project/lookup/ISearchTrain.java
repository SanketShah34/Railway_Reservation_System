package com.project.lookup;

import java.util.Date;

public interface ISearchTrain {

	public String getSourceStation();
	
	public void setSourceStation(String sourceStation);
	
	public String getDestinationStation();
	
	public void setDestinationStation(String destinationStation);
	
	public Date getDateofJourny() ;
	
	public void setDateofJourny(Date dateofJourny);
	
	public String getTrainType();
	
	public void setTrainType(String trainType);
	
	public boolean issourceStationAndDestinationStationSame(String sourceStation , String destinationStation);
	
}
