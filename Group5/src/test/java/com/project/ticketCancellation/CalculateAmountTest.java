package com.project.ticketCancellation;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;
import com.project.reservation.ReservationAbstractFactoryTest;

public class CalculateAmountTest {

	CancelTicketAbstractFactoryTest cancelTicketAbstractFactoryTest = CancelTicketAbstractFactoryTest.instance();
	CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
	
	ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
	
	@SuppressWarnings("deprecation")
	@Test
	void CalculateRefundAmountTest() {
		SearchPassengerInformationDAOMock searchPassengerInformationDAOMock  =  cancelTicketAbstractFactoryTest.createSearchPassengerInformationDAOMock();
		ICalculateAmounts calculateAmounts = cancelTicketAbstractFactory.createNewCalculateAmounts();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setAmountPaid(100.0);
		reservation.setDestinationStationId(1);
		reservation.setDistance(100);
		reservation.setReservationId(1);
		reservation.setSourceStationId(1);
		reservation.setTrainId(1);
		reservation.setTrainType("Non AC Sleeper");
		
		Date date = Date.valueOf("2021-04-04");
		reservation.setStartDate(date);
		
		List<Integer> ids =new ArrayList<>();
		ids.add(1);
		ids.add(2);
		
		double amount = calculateAmounts.CalculateRefundAmount(reservation, ids, searchPassengerInformationDAOMock);
		assertEquals(amount, 350.0);
	}
	
	@Test
	void CalculateDiscountTest() {
		ICalculateAmounts calculateAmounts = cancelTicketAbstractFactory.createNewCalculateAmounts();
		Date date = Date.valueOf("2021-04-04");
		double amount = calculateAmounts.CalculateDiscount(1000.0, 500.0, date, "09:00");
		assertEquals(amount, 350.0);
	}

}
