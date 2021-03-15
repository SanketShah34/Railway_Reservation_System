package com.project.reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.logic.findFare;
import com.project.logic.findFareImpl;

public class Reservation {
	
	public int reservationId;
	public int trainId;
    public int sourceStationId;
    public int destinationStationId;
    public String pnrNumber;
    public double amountPaid;
    public int distance;
    public String trainType;
    public List<PassengerInformation> passengerInformation;
    
    public Reservation() {
    	this.passengerInformation = new ArrayList<PassengerInformation>(6);
    	this.passengerInformation.add(new PassengerInformation());
    	this.passengerInformation.add(new PassengerInformation());
    	this.passengerInformation.add(new PassengerInformation());
    	this.passengerInformation.add(new PassengerInformation());
    	this.passengerInformation.add(new PassengerInformation());
    	this.passengerInformation.add(new PassengerInformation());
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
	public double getAmountPaid() {
		return amountPaid;
	}
	public void setAmountPaid(double amountPaid) {
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

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	
	

	public void calculateReservationFarePerPassenger(Reservation reservation) {
		findFareImpl findFare = new findFareImpl();
		try {
			double fareBasedOnTrainType = findFare.calculateFareByTrainType(reservation.distance, reservation.trainType);
			double fareBasedOnDistance = findFare.calculateFareByDistance(reservation.distance, fareBasedOnTrainType);
			for ( int index = 0; index < reservation.passengerInformation.size(); index++) {
				
				reservation.passengerInformation.get(index).amountPaid = findFare.calculateFareByAge(fareBasedOnDistance, reservation.passengerInformation.get(index).age);
			}		
		} catch(Exception e) {
			System.err.print(e);
		}
	}    
	
	public void calculateTotalReservationFare(Reservation reservation) {
		for ( int index = 0; index < reservation.passengerInformation.size(); index++) {
			reservation.amountPaid = reservation.amountPaid + reservation.passengerInformation.get(index).amountPaid;
		}
	}
    
}
