package com.project.calculation;

import java.sql.Date;
import java.util.List;
import com.project.setup.ITrain;

public interface ISeatAvailibilityDAO {

	public List<IBookedTickets> getListOfTicketsFromSeatNo(ITrain train, Date date, int seatNo);

	public List<Integer> getReservationId(ITrain train, Date date);

	public int maximumSeatNumberOfReservationId(int reservationId);

}
