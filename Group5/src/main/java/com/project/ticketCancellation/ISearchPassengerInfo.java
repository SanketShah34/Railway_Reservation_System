package com.project.ticketCancellation;

import java.util.List;

import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;

public interface ISearchPassengerInfo {
	public List<IPassengerInformation> SearchPassengerInfoByPNR(String pnrNumber);
	public IReservation GetAmountPaidOnTicket(List<Integer> ids);
	public String GetPnrNumber(int id);
	public double CalculateRefundAmount(IReservation reservation, List<Integer> ids);
	public void DeleteTickets(List<Integer> ids, IReservation reservation, double refundedAmount);
}
