package com.project.cancelTrain;

import java.sql.Date;
import java.time.DayOfWeek;
import java.util.List;

import com.project.calculation.ISeatAvailibilityDAO;
import com.project.lookup.ISearchTrainDAO;
import com.project.reservation.IReservation;
import com.project.setup.IRouteDAO;

public interface ITrainCancellation {

	void cancelOrRescheduleTicket(List<IReservation> reservationList, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO);

	void rescheduleOnWeekDays(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO);

	void rescheduleOnWeekEnds(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO);

	void bookTicket(IReservation reservation);

	void cancelTicket(IReservation reservation);

	DayOfWeek findDay(Date date);

	Date getNextDate(Date date);

	Date getDateAfterAWeek(Date date);

}