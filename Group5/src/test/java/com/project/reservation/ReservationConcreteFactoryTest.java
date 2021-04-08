package com.project.reservation;

public class ReservationConcreteFactoryTest extends ReservationAbstractFactoryTest {

	@Override
	public ReservationMock createReservationMock() {
		return new ReservationMock();
	}

	@Override
	public PassengerMock createPassengerMock() {
		return new PassengerMock();
	}

	@Override
	public ReservationDAOMock createReservationDAOMock() {
		return new ReservationDAOMock();
	}

}
