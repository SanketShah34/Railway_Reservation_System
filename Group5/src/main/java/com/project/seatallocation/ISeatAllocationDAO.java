package com.project.seatallocation;

import com.project.reservation.IReservation;

public interface ISeatAllocationDAO {

	public IReservation allocateSeat(IReservation reservation);
}
