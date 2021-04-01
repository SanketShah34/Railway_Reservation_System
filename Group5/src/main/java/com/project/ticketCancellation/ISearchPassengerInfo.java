package com.project.ticketCancellation;

import java.util.List;

import com.project.reservation.IPassengerInformation;

public interface ISearchPassengerInfo {
	public List<IPassengerInformation> SearchPassengerInfoByPNR(String userName, String pnrNumber);
	public double getAmountPaidOnTicket(List<Integer> ids);
	public String getPnrNumber(int id);
	public double calculateRefundAmount(double amountPaid);
}
