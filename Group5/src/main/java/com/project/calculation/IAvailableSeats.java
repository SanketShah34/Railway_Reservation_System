package com.project.calculation;

import java.util.List;
import com.project.lookup.ISearchTrain;
import com.project.setup.ITrain;

public interface IAvailableSeats {
	
	public List<ITrain> findAvailableSeats(List<ITrain> trains,ISearchTrain searchTrain,String sourceStationName,String destinationStationName,ISeatAvailibilityDAO seatAvalibilityDAO);

}
