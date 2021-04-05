package com.project.ticketCancellation;

import static org.junit.Assert.assertEquals;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

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
		ICalculateAmounts calculateAmounts = cancelTicketAbstractFactory.createNewCalculateAmounts();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setAmountPaid(100.0);
		reservation.setDestinationStationId(1);
		reservation.setDistance(100);
		reservation.setPnrNumber("1");
		reservation.setReservationId(1);
		reservation.setSourceStationId(1);
		reservation.setTrainId(1);
		reservation.setTrainType("Non AC Sleeper");
		
		String dateStr = "2021-04-05";
		try {
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			reservation.setStartDate(date);
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		
		List<Integer> ids =new ArrayList<>();
		ids.add(1);
		ids.add(2);
		
		double amount = calculateAmounts.CalculateRefundAmount(reservation, ids);
		assertEquals(amount, 350.0);
	}
	
	@Test
	void CalculateDiscountTest() {
		ICalculateAmounts calculateAmounts = cancelTicketAbstractFactory.createNewCalculateAmounts();
		
		String dateStr = "2021-03-05";
		try {
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
			//reservation.setStartDate(date);
			double amount = calculateAmounts.CalculateDiscount(1000.0, 500.0, date, "09:00");
			assertEquals(amount, 350.0);
			
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
	}

}
