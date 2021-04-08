package com.project.reservation;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class SeatAllocationTest {
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
	ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
	
	@Test
	void testAllocateSeat() {
		SeatAllocationDAOMock seatAllocationDAOMock = reservationAbstractFactoryTest.createSeatAllocationDAOMock();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		assertNotNull(seatAllocationDAOMock.allocateSeat(reservation));
	}
}