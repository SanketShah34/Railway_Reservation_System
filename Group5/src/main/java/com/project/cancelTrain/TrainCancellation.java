package com.project.cancelTrain;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

import com.project.lookup.IAvailableSeats;
import com.project.lookup.ISearchTrain;
import com.project.lookup.ISearchTrainDAO;
import com.project.lookup.ISeatAvailibilityDAO;
import com.project.lookup.ITrainFilterAndFairCalculation;
import com.project.lookup.LookupAbstractFactory;
import com.project.reservation.IReservation;
import com.project.reservation.IReservationDAO;
import com.project.reservation.ReservationAbstractFactory;
import com.project.setup.ICancelTrain;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

public class TrainCancellation {

	private ICancelTrain cancelTrain;
	private final static String cancelTicket = "Cancel Ticket";
	private final static String rescheduleOnWeekDays = "Reschedule on week-days";
	private final static String rescheduleOnWeekEnds = "Reschedule on week-ends";
	
	public void cancelOrRescheduleTicket(List<IReservation> reservationList) {
		for(int index = 0; index < reservationList.size(); index++) {
			IReservation reservation = reservationList.get(index);
			if (reservation.getTrainCancelEvent().equals(cancelTicket)) {
				// cancel ticket
			} else if (reservation.getTrainCancelEvent().equals(rescheduleOnWeekDays)) {
				// find ticket with days as monday, tuesday, wedesday, thursday, friday
				this.rescheduleOnWeekDays(reservation); 
			} else if (reservation.getTrainCancelEvent().equals(rescheduleOnWeekEnds)) {
				// find ticket with days as saturday, sunday
			} 
		}
	}
	
	public void rescheduleOnWeekDays(IReservation reservation) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		
		ISearchTrainDAO searchTrainDAO = lookupAbstractFactory.createSearchTrainDAO();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		ITrainFilterAndFairCalculation trainFilterAndCalculateFair = lookupAbstractFactory.createNewTrainFilterAndCalculateFair();
		IRouteDAO routeDAO = setupAbstractFactory.createNewRouteDAO();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();
		ISeatAvailibilityDAO seatAvaillibilityDAO = lookupAbstractFactory.createNewSeatAvailibilityDAO();
		
		searchTrain.setTrainType(reservation.getTrainType());
		searchTrain.setDestinationStation(Integer.toString(reservation.getDestinationStationId()));
		searchTrain.setSourceStation(Integer.toString(reservation.getSourceStationId()));
		
		Date currentDate = reservation.getStartDate();
		Date dateAfterAWeek = this.getDateAfterAWeek(currentDate);
		
		boolean trainFound = false;
		while(currentDate.before(dateAfterAWeek)) {
			currentDate = this.getNextDate(currentDate);
			DayOfWeek dayOfWeek = this.findDay(currentDate);
			if (dayOfWeek.equals(DayOfWeek.MONDAY) || dayOfWeek.equals(DayOfWeek.TUESDAY) || dayOfWeek.equals(DayOfWeek.WEDNESDAY) || dayOfWeek.equals(DayOfWeek.THURSDAY) || dayOfWeek.equals(DayOfWeek.FRIDAY)) {
				searchTrain.setDateofJourny(currentDate);
				List<ITrain> trainList = searchTrainDAO.searchTrains(searchTrain);
				List<ITrain> trainListWithFairCalculation = trainFilterAndCalculateFair.filterTrain(trainList, searchTrain,routeDAO);
				availableSeats.findAvailableSeats(trainListWithFairCalculation, searchTrain, seatAvaillibilityDAO);
				boolean ticketBooked = false;
				for (int index = 0; index < trainListWithFairCalculation.size(); index++) {
					if (trainListWithFairCalculation.get(index).getAvailableSeat() >= reservation.getTicketBooked()) {
						// book the ticket.
						ticketBooked = true;
						break;
					}
				}
				if (ticketBooked) {
					trainFound = true;
					break;
				} 
			}	
		}
		if (trainFound == false) {
			// cancel ticket.
		}
	}
	
	public void rescheduleOnWeekEnds(IReservation reservation) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
//		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		
		ISearchTrainDAO searchTrainDAO = lookupAbstractFactory.createSearchTrainDAO();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		ITrainFilterAndFairCalculation trainFilterAndCalculateFair = lookupAbstractFactory.createNewTrainFilterAndCalculateFair();
		IRouteDAO routeDAO = setupAbstractFactory.createNewRouteDAO();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();
		ISeatAvailibilityDAO seatAvaillibilityDAO = lookupAbstractFactory.createNewSeatAvailibilityDAO();
		
		searchTrain.setTrainType(reservation.getTrainType());
		searchTrain.setDestinationStation(Integer.toString(reservation.getDestinationStationId()));
		searchTrain.setSourceStation(Integer.toString(reservation.getSourceStationId()));
		
		Date currentDate = reservation.getStartDate();
		Date dateAfterAWeek = this.getDateAfterAWeek(currentDate);
		
		boolean trainFound = false;
		while(currentDate.before(dateAfterAWeek)) {
			currentDate = this.getNextDate(currentDate);
			DayOfWeek dayOfWeek = this.findDay(currentDate);
			if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
				searchTrain.setDateofJourny(currentDate);
				List<ITrain> trainList = searchTrainDAO.searchTrains(searchTrain);
				List<ITrain> trainListWithFairCalculation = trainFilterAndCalculateFair.filterTrain(trainList, searchTrain,routeDAO);
				availableSeats.findAvailableSeats(trainListWithFairCalculation, searchTrain, seatAvaillibilityDAO);
				boolean ticketBooked = false;
				for (int index = 0; index < trainListWithFairCalculation.size(); index++) {
					ITrain train = trainListWithFairCalculation.get(index); 
					if (train.getAvailableSeat() >= reservation.getTicketBooked()) {
						// book the ticket.
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
		}
		if (trainFound == false) {
			// cancel ticket.
		}
	}
	
	public void bookTicket(IReservation reservation) {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		IReservationDAO reservationDAO = reservationAbstractFactory.createNewReservationDAO();
		reservation.calculateTotalReservationFare(reservation);
		IReservation reservationInformation = reservationDAO.saveReservationInformation(reservation);
		reservationDAO.savePassengerInformation(reservationInformation);
	}
	
	public DayOfWeek findDay(Date date) {
		LocalDate localDate = date.toLocalDate();
		return localDate.getDayOfWeek();
	}
	
	public Date getNextDate(Date date) {
		LocalDate localDate = date.toLocalDate();
		return Date.valueOf(localDate.plusDays(1));
	}
	
	public Date getDateAfterAWeek(Date date) {
		LocalDate localDateAfterAWeek = date.toLocalDate().plusDays(8);
		return Date.valueOf(localDateAfterAWeek);
	}
	
}
