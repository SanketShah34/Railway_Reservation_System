package com.project.calculation;

import java.sql.Date;
import java.util.List;
import com.project.lookup.ISearchTrain;
import com.project.setup.ITrain;

public interface IAvailableSeats {

	public List<ITrain> findAvailableSeats(List<ITrain> trains, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvalibilityDAO);

	public List<Integer> listOfMiddleStation(ITrain train, ISearchTrain searchTrain);

	public void findAvailableSeatCountInSingleTrain(ITrain train, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvaillibilityDAO);

	public int findMaximumSeatInSingleTrain(ITrain train, Date date, ISeatAvailibilityDAO seatAvaillibilityDAO);

}
