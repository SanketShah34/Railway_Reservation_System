package com.project.seatallocation;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;

public class SeatAllocationTest {

	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();

	@Test
	void testAllocateSeat() {
//		ISeatAllocationDAO seatAllocationDAO = new SeatAllocationDAO();
//		IReservation reservation = reservationAbstractFactory.createNewReservation();
//		assertNotNull(seatAllocationDAO.allocateSeat(reservation));
	}
}