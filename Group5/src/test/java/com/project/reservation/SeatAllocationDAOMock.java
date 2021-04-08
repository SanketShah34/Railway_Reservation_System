package com.project.reservation;

import com.project.reservation.IReservation;
import com.project.reservation.ISeatAllocationDAO;

public class SeatAllocationDAOMock implements ISeatAllocationDAO {

	@Override
	public IReservation allocateSeat(IReservation reservation) {
		reservation.setReservationId(1);
		return reservation;
	}
}
