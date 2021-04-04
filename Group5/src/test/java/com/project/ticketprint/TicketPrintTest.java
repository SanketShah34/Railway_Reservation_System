package com.project.ticketprint;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.reservation.IPassengerInformation;
import com.project.reservation.PassengerInformation;

public class TicketPrintTest {
	
	TicketPrintAbstractFactoryTest ticketPrintAbstractFactoryTest = TicketPrintAbstractFactoryTest.instance();
	TicketPrintAbstractFactory ticketPrintAbstractFactory = TicketPrintAbstractFactory.instance();
	
	@Test
	void testGetReservationId() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setReservationId(1);
		assertEquals(ticketPrint.getReservationId(), 1);
	}

	@Test
	void testSetReservationId() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setReservationId(1);
		assertEquals(ticketPrint.getReservationId(), 1);
	}
	
	@Test
	void testGetTrainCode() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setTrainCode(1);
		assertEquals(ticketPrint.getTrainCode(), 1);
	}

	@Test
	void testSetTrainCode() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setTrainCode(1);
		assertEquals(ticketPrint.getTrainCode(), 1);
	}
	
	@Test
	void testGetTrainName() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setTrainName("ABC");
		assertEquals(ticketPrint.getTrainName(), "ABC");
	}

	@Test
	void testSetTrainName() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setTrainName("ABC");
		assertEquals(ticketPrint.getTrainName(), "ABC");
	}
	
	@Test
	void testGetSourceStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setSourceStation("A");
		assertEquals(ticketPrint.getSourceStation(), "A");
	}

	@Test
	void testSetSourceStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setSourceStation("A");
		assertEquals(ticketPrint.getSourceStation(), "A");
	}
	
	@Test
	void testGetDestinationStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setSourceStation("A");
		assertEquals(ticketPrint.getSourceStation(), "A");
	}

	@Test
	void testSetDestinationStation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setDestinationStation("A");
		assertEquals(ticketPrint.getDestinationStation(), "A");
	}
	
	@Test
	void testGetAmountPaid() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setAmountPaid(100.0);
		assertEquals(String.valueOf(ticketPrint.getAmountPaid()), "100.0");
	}

	@Test
	void testSetAmountPaid() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setAmountPaid(100.0);
		assertEquals(String.valueOf(ticketPrint.getAmountPaid()), "100.0");
	}
	
	@Test
	void testGetTrainType() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setTrainType("AC");
		assertEquals(ticketPrint.getTrainType(), "AC");
	}

	@Test
	void testSetTrainType() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		ticketPrint.setTrainType("AC");
		assertEquals(ticketPrint.getTrainType(), "AC");
	}
	
	@Test
	void testGetPassengerInformation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		PassengerInformation passenger = new PassengerInformation();
		passenger.setAge(23);
		passengerInformation.add(passenger);
		ticketPrint.setPassengerInformation(passengerInformation);
		assertEquals(ticketPrint.getPassengerInformation().get(0).getAge(), 23);
	}

	@Test
	void testSetPassengerInformation() {
		ITicketPrint ticketPrint = ticketPrintAbstractFactory.createNewTicketPrint();
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		PassengerInformation passenger = new PassengerInformation();
		passenger.setAge(23);
		passengerInformation.add(passenger);
		ticketPrint.setPassengerInformation(passengerInformation);
		assertEquals(ticketPrint.getPassengerInformation().get(0).getAge(), 23);
	}
}