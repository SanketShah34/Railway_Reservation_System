package com.project.calculation;

import java.sql.Date;

public class BookedTickets implements IBookedTickets {

	private int reservationId;
	private String trainId;
	private Date date;
	private int sourceStationId;
	private int destinationId;
	private double amountPaid;
	private int ticketBooked;
	private int userId;

	public int getReservationId() {
		return reservationId;
	}

	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}

	public String getTrainId() {
		return trainId;
	}

	public void setTrainId(String trainId) {
		this.trainId = trainId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getSourceStationId() {
		return sourceStationId;
	}

	public void setSourceStationId(int sourceStationId) {
		this.sourceStationId = sourceStationId;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}

	public double getAmountPaid() {
		return amountPaid;
	}

	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	public int getTicketBooked() {
		return ticketBooked;
	}

	public void setTicketBooked(int ticketBooked) {
		this.ticketBooked = ticketBooked;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

}
