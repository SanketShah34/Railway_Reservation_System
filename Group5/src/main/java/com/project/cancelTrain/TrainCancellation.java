package com.project.cancelTrain;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.project.lookup.IAvailableSeats;
import com.project.lookup.ISearchTrain;
import com.project.lookup.ISearchTrainDAO;
import com.project.lookup.ISeatAvailibilityDAO;
import com.project.lookup.ITrainFilterAndFairCalculation;
import com.project.lookup.LookupAbstractFactory;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.reservation.IReservationDAO;
import com.project.reservation.ReservationAbstractFactory;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;
import com.project.ticketCancellation.CancelTicketAbstractFactory;
import com.project.ticketCancellation.ICalculateAmounts;
import com.project.ticketCancellation.ISearchPassengerInformationDAO;

public class TrainCancellation implements ITrainCancellation {

	private final static String CANCEL_TRAIN = "Cancel Ticket";
	private final static String RESCHEDULE_ON_WEEK_DAYS = "Reschedule on week-days";
	private final static String RESCHEDULE_ON_WEEK_ENDS = "Reschedule on week-ends";
	
	@Override
	public void cancelOrRescheduleTicket(List<IReservation> reservationList, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO) {
		for(int index = 0; index < reservationList.size(); index++) {
			IReservation reservation = reservationList.get(index);
			if (reservation.getTrainCancelEvent().equals(CANCEL_TRAIN)) {
				this.cancelTicket(reservation);
			} else if (reservation.getTrainCancelEvent().equals(RESCHEDULE_ON_WEEK_DAYS)) {
				this.rescheduleOnWeekDays(reservation, searchTrainDAO, routeDAO, seatAvailibilityDAO); 
			} else if (reservation.getTrainCancelEvent().equals(RESCHEDULE_ON_WEEK_ENDS)) {
				this.rescheduleOnWeekEnds(reservation, searchTrainDAO, routeDAO, seatAvailibilityDAO);
			} 
		}
	}
	
	@Override
	public void rescheduleOnWeekDays(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();		
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		ITrainFilterAndFairCalculation trainFilterAndCalculateFair = lookupAbstractFactory.createNewTrainFilterAndCalculateFair();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();
		
		searchTrain.setTrainType(reservation.getTrainType());
		searchTrain.setDestinationStation(Integer.toString(reservation.getDestinationStationId()));
		searchTrain.setSourceStation(Integer.toString(reservation.getSourceStationId()));
		
		Date currentDate = reservation.getStartDate();
		Date dateAfterAWeek = this.getDateAfterAWeek(currentDate);
		
		boolean trainFound = false;
		while(currentDate.before(dateAfterAWeek)) {
			DayOfWeek dayOfWeek = this.findDay(currentDate);
			if (dayOfWeek.equals(DayOfWeek.MONDAY) || dayOfWeek.equals(DayOfWeek.TUESDAY) || dayOfWeek.equals(DayOfWeek.WEDNESDAY) || dayOfWeek.equals(DayOfWeek.THURSDAY) || dayOfWeek.equals(DayOfWeek.FRIDAY)) {
				searchTrain.setDateofJourny(currentDate);
				List<ITrain> trainList = searchTrainDAO.searchTrains(searchTrain);
				List<ITrain> trainListWithFareCalculation = trainFilterAndCalculateFair.filterTrain(trainList, searchTrain, routeDAO);
				availableSeats.findAvailableSeats(trainListWithFareCalculation, searchTrain, seatAvailibilityDAO);
				boolean ticketBooked = false;
				for (int index = 0; index < trainListWithFareCalculation.size(); index++) {
					ITrain train = trainListWithFareCalculation.get(index); 
					boolean sameDayDifferentTrain = true;
					if (currentDate.equals(reservation.getStartDate())) {
						if (train.getTrainId() == reservation.getTrainId()) {
							sameDayDifferentTrain = false;
						}
					}
					if (train.getAvailableSeat() >= reservation.getTicketBooked() && sameDayDifferentTrain) {
						this.cancelTicket(reservation);
						reservation.setTrainId(train.getTrainId());
						reservation.setDistance(train.getTotalDistance());
						System.out.println(train.getStartDate());
						reservation.setStartDate(train.getStartDate());
						this.bookTicket(reservation);
						
						ticketBooked = true;
						break;
					}
				}
				if (ticketBooked) {
					trainFound = true;
					break;
				} 
			}	
			currentDate = this.getNextDate(currentDate);
		}
		if (trainFound == false) {
			this.cancelTicket(reservation);
		}
	}
	
	@Override
	public void rescheduleOnWeekEnds(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		ITrainFilterAndFairCalculation trainFilterAndCalculateFair = lookupAbstractFactory.createNewTrainFilterAndCalculateFair();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();
		
		searchTrain.setTrainType(reservation.getTrainType());
		searchTrain.setDestinationStation(Integer.toString(reservation.getDestinationStationId()));
		searchTrain.setSourceStation(Integer.toString(reservation.getSourceStationId()));
		
		Date currentDate = reservation.getStartDate();
		Date dateAfterAWeek = this.getDateAfterAWeek(currentDate);
		
		boolean trainFound = false;
		while(currentDate.before(dateAfterAWeek)) {
			DayOfWeek dayOfWeek = this.findDay(currentDate);
			if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
				searchTrain.setDateofJourny(currentDate);
				List<ITrain> trainList = searchTrainDAO.searchTrains(searchTrain);
				List<ITrain> trainListWithFareCalculation = trainFilterAndCalculateFair.filterTrain(trainList, searchTrain,routeDAO);
				availableSeats.findAvailableSeats(trainListWithFareCalculation, searchTrain, seatAvailibilityDAO);
				boolean ticketBooked = false;
				for (int index = 0; index < trainListWithFareCalculation.size(); index++) {
					ITrain train = trainListWithFareCalculation.get(index);
					boolean sameDayDifferentTrain = true;
					if (currentDate.equals(reservation.getStartDate())) {
						if (train.getTrainId() == reservation.getTrainId()) {
							sameDayDifferentTrain = false;
						}
					}
					if (train.getAvailableSeat() >= reservation.getTicketBooked() && sameDayDifferentTrain) {
						this.cancelTicket(reservation);
						reservation.setTrainId(train.getTrainId());
						reservation.setDistance(train.getTotalDistance());
						reservation.setStartDate(train.getStartDate());
						this.bookTicket(reservation);
						ticketBooked = true;
						break;
					}
				}
				if (ticketBooked) {
					trainFound = true;
					break;
				} 
			}
			currentDate = this.getNextDate(currentDate);
		}
		if (trainFound == false) {
			this.cancelTicket(reservation);
		}
	}
	
	@Override
	public void bookTicket(IReservation reservation) {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		IReservationDAO reservationDAO = reservationAbstractFactory.createNewReservationDAO();
		reservation.calculateTotalReservationFare(reservation);
		IReservation reservationInformation = reservationDAO.saveReservationInformation(reservation);
		reservationDAO.savePassengerInformation(reservationInformation);
	}
	
	@Override
	public void cancelTicket(IReservation reservation) {
		List<Integer> ticketsToBeCancelled = new ArrayList<Integer>(0);
		if (reservation.getPassengerInformation().size() > 0) {
			for(int index = 0; index < reservation.getPassengerInformation().size(); index++) {
				IPassengerInformation passenger = reservation.getPassengerInformation().get(index);
				ticketsToBeCancelled.add(passenger.getPassengerInformationId());
			}
		}
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInformationDAO searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		ICalculateAmounts calculateAmounts = cancelTicketAbstractFactory.createNewCalculateAmounts();
		IReservation reservationInformation = searchTicketInfo.GetAmountPaidOnTicket(ticketsToBeCancelled);
		double refundedAmount = calculateAmounts.CalculateRefundAmount(reservationInformation, ticketsToBeCancelled);
		searchTicketInfo.DeleteTickets(ticketsToBeCancelled, reservationInformation, refundedAmount);
	}
	
	@Override
	public DayOfWeek findDay(Date date) {
		LocalDate localDate = date.toLocalDate();
		return localDate.getDayOfWeek();
	}
	
	@Override
	public Date getNextDate(Date date) {
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.plusDays(1));
	}
	
	@Override
	public Date getDateAfterAWeek(Date date) {
		LocalDate localDateAfterAWeek = date.toLocalDate().plusDays(8);
		return Date.valueOf(localDateAfterAWeek);
	}
	
}
