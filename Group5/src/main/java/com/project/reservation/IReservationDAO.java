package com.project.reservation;

public interface IReservationDAO {
	public IReservation saveReservationInformation(IReservation reservation);
	public void savePassengerInformation(IReservation reservation);
}
