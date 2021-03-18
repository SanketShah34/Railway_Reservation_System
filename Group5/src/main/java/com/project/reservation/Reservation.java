package com.project.reservation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.project.logic.findFare;
import com.project.logic.findFareImpl;

public class Reservation implements IReservation {
	
	public int reservationId;
	public int trainId;
    public int sourceStationId;
    public int destinationStationId;
    public String pnrNumber;
    public double amountPaid;
    public int distance;
    public String trainType;
    public List<IPassengerInformation> passengerInformation;
    
    public static final int numberOfPassengersPerReservation = 6;
    
    public Reservation() {
    	this.initializePassengerList();
    }
    
    private void initializePassengerList() {
    	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
    	List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(numberOfPassengersPerReservation);
    	for (int index = 0; index < numberOfPassengersPerReservation; index++) {
    		this.addInPassengerInformationList(passengerInformationList, reservationAbstractFactory.createNewPassengerInformation());	
    	}
    	this.setPassengerInformation(passengerInformationList);
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
	public List<IPassengerInformation> getPassengerInformation() {
		return passengerInformation;
	}
	
	@Override
	public void addInPassengerInformationList(List<IPassengerInformation> passengerInformationList, IPassengerInformation passengerInformation) {
		passengerInformationList.add(passengerInformation);
	}
	
	@Override
	public void setPassengerInformation(List<IPassengerInformation> passengerInformation) {
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
	public void calculateReservationFarePerPassenger(IReservation reservation) {
		findFareImpl findFare = new findFareImpl();
		try {
			double fareBasedOnTrainType = findFare.calculateFareByTrainType(reservation.getDistance(), reservation.getTrainType());
			double fareBasedOnDistance = findFare.calculateFareByDistance(reservation.getDistance(), fareBasedOnTrainType);
			int passengerInformationLength = reservation.getPassengerInformation().size();
			for ( int index = 0; index < passengerInformationLength; index++) {
				double amountPaid = findFare.calculateFareByAge(fareBasedOnDistance, reservation.getPassengerInformation().get(index).getAge());
				reservation.getPassengerInformation().get(index).setAmountPaid(amountPaid);
			}		
		} catch(Exception exception) {
			exception.printStackTrace();
		}
	}    
	
	@Override
	public void calculateTotalReservationFare(IReservation reservation) {
		this.calculateReservationFarePerPassenger(reservation);
		int passengerInformationLength = reservation.getPassengerInformation().size();
		double amountPaid = 0.0;
		for ( int index = 0; index < passengerInformationLength; index++) {
			amountPaid = amountPaid + reservation.getPassengerInformation().get(index).getAmountPaid();
		}
		reservation.setAmountPaid(amountPaid); 
	}
    
}
