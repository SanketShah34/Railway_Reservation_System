package com.project.calculation;

import java.sql.Date;
import java.util.List;

import com.project.setup.ITrain;

public class SeatAvailibilityDAOMock implements ISeatAvailibilityDAO {


	@Override
	public List<IBookedTickets> getListOfTicketsFromSeatNo(ITrain train, Date date, int seatNo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Integer> getReservationId(ITrain train, Date date) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int maximumSeatNumberOfReservationId(int reservationId) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
