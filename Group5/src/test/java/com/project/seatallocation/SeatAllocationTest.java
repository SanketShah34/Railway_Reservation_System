package com.project.seatallocation;

import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;

public class SeatAllocationTest {
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
	SeatAllocationAbstractFactoryTest seatAllocationAbstractFactoryTest = SeatAllocationAbstractFactoryTest.instance();
	SeatAllocationAbstractFactory seatAllocationAbstractFactory = SeatAllocationAbstractFactory.instance();
	
	@Test
	void testAllocateSeat() {
		SeatAllocationDAOMock seatAllocationDAOMock = seatAllocationAbstractFactoryTest.createSeatAllocationDAOMock();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		assertNotNull(seatAllocationDAOMock.allocateSeat(reservation));
	}
}