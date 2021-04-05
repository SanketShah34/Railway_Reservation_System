package com.project.ticketCancellation;

import java.util.List;

import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.setup.ITrain;

public interface ISearchPassengerInformationDAO {
	public List<IPassengerInformation> SearchPassengerInfoByPNR(String pnrNumber);
	public IReservation GetAmountPaidOnTicket(List<Integer> ids);
	public ITrain GetTrainDetails(int trainId);
	public String GetPnrNumber(int id);
	public void DeleteTickets(List<Integer> ids, IReservation reservation, double refundedAmount);
}
