package com.project.seatallocation;

import com.project.reservation.IReservation;

public interface ISeatAllocationDAO {

	IReservation allocateSeat(IReservation reservation);

}
