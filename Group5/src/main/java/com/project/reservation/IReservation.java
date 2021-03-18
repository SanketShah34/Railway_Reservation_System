package com.project.reservation;

import java.util.List;

public interface IReservation {

	int getReservationId();

	void setReservationId(int reservationId);

	int getTrainId();

	void setTrainId(int trainId);

	int getSourceStationId();

	void setSourceStationId(int sourceStationId);

	int getDestinationStationId();

	void setDestinationStationId(int destinationStationId);

	String getPnrNumber();

	void setPnrNumber(String pnrNumber);

	double getAmountPaid();

	void setAmountPaid(double amountPaid);

	List<IPassengerInformation> getPassengerInformation();

	void setPassengerInformation(List<IPassengerInformation> passengerInformation);

	void setDistance(int distance);

	int getDistance();

	String getTrainType();

	void setTrainType(String trainType);

	void calculateReservationFarePerPassenger(IReservation reservation);

	void calculateTotalReservationFare(IReservation reservation);

	void addInPassengerInformationList(List<IPassengerInformation>passengerInformationList, IPassengerInformation passengerInformation);

}