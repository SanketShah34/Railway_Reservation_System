package com.project.reservation;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class ReservationTest {

	ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();

	@Test
	void testGetReservationId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setReservationId(1);
		assertEquals(reservation.getReservationId(), 1);
	}

	@Test
	void testSetReservationId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setReservationId(1);
		assertEquals(reservation.getReservationId(), 1);
	}

	@Test
	void testGetTrainId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTrainId(1);
		assertEquals(reservation.getTrainId(), 1);
	}

	@Test
	void testSetTrainId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTrainId(1);
		assertEquals(reservation.getTrainId(), 1);
	}

	@Test
	void testGetSourceStationId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setSourceStationId(1);
		assertEquals(reservation.getSourceStationId(), 1);
	}

	@Test
	void testSetSourceStationId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setSourceStationId(1);
		assertEquals(reservation.getSourceStationId(), 1);
	}

	@Test
	void testGetDestinationStationId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setDestinationStationId(1);
		assertEquals(reservation.getDestinationStationId(), 1);
	}

	@Test
	void testSetDestinationStationId() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setDestinationStationId(1);
		assertEquals(reservation.getDestinationStationId(), 1);
	}

	@Test
	void testGetPnrNumber() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setPnrNumber("100AB");
		assertEquals(reservation.getPnrNumber(), "100AB");
	}

	@Test
	void testSetPnrNumber() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setPnrNumber("100AB");
		assertEquals(reservation.getPnrNumber(), "100AB");
	}

	@Test
	void testGetAmountPaid() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setAmountPaid(100.0);
		assertEquals(reservation.getAmountPaid(), 100.0, 0.2);
	}

	@Test
	void testSetAmountPaid() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setAmountPaid(100.0);
		assertEquals(reservation.getAmountPaid(), 100.0, 0.2);
	}

	@Test
	void testGetPassengerInformation() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>();
		reservation.addInPassengerInformationList(passengerInformationList, passenger);
		reservation.setPassengerInformation(passengerInformationList);
		assertEquals(reservation.getPassengerInformation().size(), 1);
	}

	@Test
	void testSetPassengerInformation() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>();
		reservation.addInPassengerInformationList(passengerInformationList, passenger);
		reservation.setPassengerInformation(passengerInformationList);
		assertEquals(reservation.getPassengerInformation().size(), 1);
	}

	@Test
	void testSetDistance() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setDistance(100);
		assertEquals(reservation.getDistance(), 100);
	}

	@Test
	void testGetDistance() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setDistance(100);
		assertEquals(reservation.getDistance(), 100);
	}

	@Test
	void testGetTrainType() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTrainType("Non AC Sleeper");
		assertEquals(reservation.getTrainType(), "Non AC Sleeper");
	}

	@Test
	void testSetTrainType() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTrainType("Non AC Sleeper");
		assertEquals(reservation.getTrainType(), "Non AC Sleeper");
	}

	@Test
	void testCalculateTotalReservationFare() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		reservation = reservationMock.creteReservationMock(reservation);
		
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		passenger = passengerMock.createPassengerMock(passenger);
		
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>();
		reservation.addInPassengerInformationList(passengerInformationList, passenger);
		reservation.setPassengerInformation(passengerInformationList);
		reservation.calculateTotalReservationFare(reservation);
		
		assertEquals(reservation.getAmountPaid(), 168.0, 0.2);
	}

}
