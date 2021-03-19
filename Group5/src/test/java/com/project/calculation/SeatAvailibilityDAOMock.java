package com.project.calculation;

import java.util.Date;

public class SeatAvailibilityDAOMock implements ISeatAvailibilityDAO {

	@Override
	public int bookedTickets(String sourceStation, String destinationStation, int trainID, Date date) {
		return 10;
	}

	@Override
	public String convertDateUtilIntoSql(Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	
}
