package com.project.ticketemail;

import java.util.List;

import com.project.reservation.IPassengerInformation;

public class TicketEmail implements ITicketEmail{

	public int reservationId;
	public int trainCode;
	public String trainName;
    public String sourceStation;
    public String destinationStation;
    public double amountPaid;
    public String trainType;
    public List<IPassengerInformation> passengerInformation;
    
    public TicketEmail() {
    }

	public TicketEmail(int reservationId, int trainCode, String trainName, String sourceStation,
			String destinationStation, double amountPaid, String trainType,
			List<IPassengerInformation> passengerInformation) {
		super();
		this.reservationId = reservationId;
		this.trainCode = trainCode;
		this.trainName = trainName;
		this.sourceStation = sourceStation;
		this.destinationStation = destinationStation;
		this.amountPaid = amountPaid;
		this.trainType = trainType;
		this.passengerInformation = passengerInformation;
	}

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public int getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(int trainCode) {
		this.trainCode = trainCode;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public List<IPassengerInformation> getPassengerInformation() {
		return passengerInformation;
	}

	public void setPassengerInformation(List<IPassengerInformation> passengerInformation) {
		this.passengerInformation = passengerInformation;
	}
}