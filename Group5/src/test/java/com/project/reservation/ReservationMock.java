package com.project.reservation;

public class ReservationMock {

	public IReservation creteReservationMock(IReservation reservation) {
		reservation.setAmountPaid(100.0);
		reservation.setDestinationStationId(1);
		reservation.setDistance(100);
		reservation.setPnrNumber("100AB");
		reservation.setReservationId(1);
		reservation.setSourceStationId(1);
		reservation.setTrainId(1);
		reservation.setTrainType("Non AC Sleeper");
		return reservation;
	}
}
