package com.project.seatallocation;

import com.project.reservation.IReservation;

public class SeatAllocationDAOMock implements ISeatAllocationDAO {

	@Override
	public IReservation allocateSeat(IReservation reservation) {
		reservation.setReservationId(1);
		return reservation;
	}
}
