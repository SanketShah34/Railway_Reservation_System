package com.project.reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.calculation.IFindFare;
import com.project.calculation.FindFare;

public class Reservation implements IReservation {
	
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
    
	@Override
	public int getReservationId() {
		return reservationId;
	}
	@Override
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	@Override
	public int getTrainId() {
		return trainId;
	}
	@Override
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}
	@Override
	public int getSourceStationId() {
		return sourceStationId;
	}
	@Override
	public void setSourceStationId(int sourceStationId) {
		this.sourceStationId = sourceStationId;
	}
	@Override
	public int getDestinationStationId() {
		return destinationStationId;
	}
	@Override
	public void setDestinationStationId(int destinationStationId) {
		this.destinationStationId = destinationStationId;
	}
	@Override
	public String getPnrNumber() {
		return pnrNumber;
	}
	@Override
	public void setPnrNumber(String pnrNumber) {
		this.pnrNumber = pnrNumber;
	}
	@Override
	public double getAmountPaid() {
		return amountPaid;
	}
	@Override
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	@Override
	public List<PassengerInformation> getPassengerInformation() {
		return passengerInformation;
	}
	@Override
	public void setPassengerInformation(List<PassengerInformation> passengerInformation) {
		this.passengerInformation = passengerInformation;
	}
	@Override
	public void setDistance(int distance) {
		this.distance = distance;
	}
	@Override
	public int getDistance() {
		return this.distance;
	}

	@Override
	public String getTrainType() {
		return trainType;
	}

	@Override
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}
	
	

	@Override
	public void calculateReservationFarePerPassenger(Reservation reservation) {
		FindFare findFare = new FindFare();
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
	
	@Override
	public void calculateTotalReservationFare(Reservation reservation) {
		for ( int index = 0; index < reservation.passengerInformation.size(); index++) {
			reservation.amountPaid = reservation.amountPaid + reservation.passengerInformation.get(index).amountPaid;
		}
	}
    
}
