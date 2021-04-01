package com.project.calculation;

import java.sql.Date;

public interface IBookedTickets {

	int getReservationId();

	void setReservationId(int reservationId);

	String getTrainId();

	void setTrainId(String trainId);

	Date getDate();

	void setDate(Date date);

	int getSourceStationId();

	void setSourceStationId(int sourceStationId);

	int getDestinationId();

	void setDestinationId(int destinationId);

	double getAmountPaid();

	void setAmountPaid(double amountPaid);

	int getTicketBooked();

	void setTicketBooked(int ticketBooked);

	int getUserId();

	void setUserId(int userId);

}
