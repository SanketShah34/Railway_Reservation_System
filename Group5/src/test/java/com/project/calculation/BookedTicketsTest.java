package com.project.calculation;

import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class BookedTicketsTest {

	CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
	CalculationAbstractFactoryTest calculationAbstractFactoryTest = CalculationAbstractFactoryTest.instance();

	@Test
	void testGetReservationId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setReservationId(1);
		assertEquals(1 ,bookedTickets.getReservationId());
	}

	@Test
	void testSetReservationId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setReservationId(1);
		assertEquals(1 ,bookedTickets.getReservationId());
	}

	@Test
	void testGetTrainId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setTrainId(1);
		assertEquals(1 ,bookedTickets.getTrainId());
	}

	@Test
	void testSetTrainId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setTrainId(1);
		assertEquals(1 ,bookedTickets.getTrainId());
	}

	@Test
	void testGetDate() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setReservationDate(new Date(61202516585000L));
		Date date = new Date(61202516585000L);
		assertEquals(date ,bookedTickets.getReservationDate());
	}

	@Test
	void testSetDate() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setReservationDate(new Date(61202516585000L));
		Date date = new Date(61202516585000L);
		assertEquals(date ,bookedTickets.getReservationDate());
	}

	@Test
	void testGetSourceStationId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setSourceStationId(1);
		assertEquals(1 ,bookedTickets.getSourceStationId());
	}

	@Test
	void testSetSourceStationId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setSourceStationId(1);
		assertEquals(1 ,bookedTickets.getSourceStationId());
	}

	@Test
	void testGetDestinationId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setDestinationId(1);
		assertEquals(1 ,bookedTickets.getDestinationId());
	}

	@Test
	void testSetDestinationId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setDestinationId(1);
		assertEquals(1 ,bookedTickets.getDestinationId());
	}

	@Test
	void testGetAmountPaid() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setAmountPaid(100);
		assertEquals(100 ,bookedTickets.getAmountPaid());
	}

	@Test
	void testSetAmountPaid() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setAmountPaid(100);
		assertEquals(100 ,bookedTickets.getAmountPaid());
	}

	@Test
	void testGetTicketBooked() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setTicketBooked(3);
		assertEquals(3 ,bookedTickets.getTicketBooked());
	}

	@Test
	void testSetTicketBooked() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setTicketBooked(3);
		assertEquals(3 ,bookedTickets.getTicketBooked());
	}

	@Test
	void testGetUserId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setUserId(5);
		assertEquals(5 ,bookedTickets.getUserId());
	}

	@Test
	void testSetUserId() {
		IBookedTickets bookedTickets = calculationAbstractFactory.createBookedTickets();
		bookedTickets.setUserId(5);
		assertEquals(5 ,bookedTickets.getUserId());
	}

}
