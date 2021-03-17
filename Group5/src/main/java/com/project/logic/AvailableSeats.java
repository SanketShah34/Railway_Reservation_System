package com.project.logic;

import java.util.List;

import com.project.entity.SearchTrain;
import com.project.setup.Station;
import com.project.setup.Train;

public interface AvailableSeats {
	
	public List<Train> findAvailableSeats(List<Train> trains , SearchTrain searchTrain , String sourceStationName , String destinationStationName );

}
