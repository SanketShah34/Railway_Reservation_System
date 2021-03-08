package com.project.reservation;

import java.util.ArrayList;
import java.util.List;

public class Reservation {
	
	public int reservationId;
	public int trainId;
    public int sourceStationId;
    public int destinationStationId;
    public String pnrNumber;
    public int amountPaid;
    public int distance;
    public List<PassengerInformation> passengerInformation;
    
    public Reservation() {
    	this.passengerInformation = new ArrayList<PassengerInformation>(6);
    }
    
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public int getTrainId() {
		return trainId;
	}
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	public int getSourceStationId() {
		return sourceStationId;
	}
	public void setSourceStationId(int sourceStationId) {
		this.sourceStationId = sourceStationId;
	}
	public int getDestinationStationId() {
		return destinationStationId;
	}
	public void setDestinationStationId(int destinationStationId) {
		this.destinationStationId = destinationStationId;
	}
	public String getPnrNumber() {
		return pnrNumber;
	}
	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	public int getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(int amountPaid) {
		this.amountPaid = amountPaid;
	}
	public List<PassengerInformation> getPassengerInformation() {
		return passengerInformation;
	}
	public void setPassengerInformation(List<PassengerInformation> passengerInformation) {
		this.passengerInformation = passengerInformation;
	}
	public void setDistance(int distance) {
		this.distance = distance;
	}
	public int getDistance() {
		return this.distance;
	}
    
    
}
