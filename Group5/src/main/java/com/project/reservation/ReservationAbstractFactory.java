package com.project.reservation;

public abstract class ReservationAbstractFactory {
	private static ReservationAbstractFactory instance = null;
	
	public abstract IReservation createReservation();
	public abstract IReservation createNewReservation();
	public abstract IPassengerInformation createPassengerInformation();
	public abstract IPassengerInformation createNewPassengerInformation();
	
	public static ReservationAbstractFactory instance() {
        if (instance == null) {
            instance = new ReservationConcrete();
        }
        return instance;
    }
}
