package com.project.ticketCancellation;

import java.util.ArrayList;
import java.util.List;

import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.reservation.PassengerMock;
import com.project.reservation.ReservationAbstractFactory;
import com.project.reservation.ReservationAbstractFactoryTest;
import com.project.reservation.ReservationMock;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;
import com.project.setup.TrainMock;

public class SearchPassengerInformationDAOMock implements ISearchPassengerInformationDAO {

	@Override
	public List<IPassengerInformation> SearchPassengerInfoByPNR(String pnrNumber) {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		passenger = passengerMock.createPassengerMock(passenger);
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
		return passengerInformationList;
	}

	@Override
	public IReservation GetAmountPaidOnTicket(List<Integer> ids) {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		reservation = reservationMock.creteReservationMock(reservation);
		return reservation;
	}

	@Override
	public ITrain GetTrainDetails(int trainId) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		train = trainMock.createTrainMock(train);
		return train;
	}

	@Override
	public String GetPnrNumber(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void DeleteTickets(List<Integer> ids, IReservation reservation, double refundedAmount) {
		Double amountPaid = reservation.getAmountPaid();
		Double finalAmount = amountPaid - refundedAmount;
		int totalTicketBooked = reservation.getTicketBooked();
		int remainingTickets = totalTicketBooked - ids.size();
		int deletedTicket = 0;
		if(remainingTickets == 0) {
			deletedTicket = 1;
		}
		reservation.setAmountPaid(finalAmount);
		reservation.setDeletedTicket(deletedTicket);
	}

}
