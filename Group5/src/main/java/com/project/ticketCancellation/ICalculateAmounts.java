package com.project.ticketCancellation;

import java.sql.Date;
import java.util.List;

import com.project.reservation.IReservation;

public interface ICalculateAmounts {
	public double CalculateDiscount(double amountPaid, double refundedAmount, Date trainStartDate, String departureTime);
	
	public double CalculateRefundAmount(IReservation reservation, List<Integer> ids, ISearchPassengerInformationDAO searchTicketInfo);
}
