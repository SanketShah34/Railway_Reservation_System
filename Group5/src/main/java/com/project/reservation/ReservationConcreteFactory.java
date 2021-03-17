package com.project.reservation;

public class ReservationConcreteFactory extends ReservationAbstractFactory {
	public IPassengerInformation passengerInformation;
	public IReservation reservation;
	
	public IReservation createReservation() {
		if  (reservation == null) {
			reservation = new Reservation();
		}
		return reservation;
	}
	
	public IReservation createNewReservation() {
		return new Reservation();
	}
	
	public IPassengerInformation createPassengerInformation() {
		if (passengerInformation == null) {
			passengerInformation = new PassengerInformation();
		}
		return passengerInformation;
	}
	
	public IPassengerInformation createNewPassengerInformation() {
		return new PassengerInformation();
	}
}
