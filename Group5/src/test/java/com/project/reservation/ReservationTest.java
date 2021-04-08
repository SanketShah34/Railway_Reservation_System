package com.project.reservation;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.sql.Date;
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
		assertEquals(reservation.getDistance(), 100, 0.2);
	}

	@Test
	void testGetDistance() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setDistance(100);
		assertEquals(reservation.getDistance(), 100, 0.2);
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
	void testGetTrainCancelEvent() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTrainCancelEvent("Cancel Train");
		assertEquals(reservation.getTrainCancelEvent(), "Cancel Train");
	}

	@Test
	void testSetTrainCancelEvent() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTrainCancelEvent("Cancel Train");
		assertEquals(reservation.getTrainCancelEvent(), "Cancel Train");
	}
	
	@Test
	void testGetStartDate() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		Date date = Date.valueOf("2021-04-08");
		reservation.setStartDate(date);
		assertEquals(reservation.getStartDate(), date);
	}

	@Test
	void testSetStartDate() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		Date date = Date.valueOf("2021-04-08");
		reservation.setStartDate(date);
		assertEquals(reservation.getStartDate(), date);
	}
	
	@Test
	void testGetTicketBooked() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTicketBooked(1);
		assertEquals(reservation.getTicketBooked(), 1);
	}

	@Test
	void testSetTicketBooked() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setTicketBooked(1);
		assertEquals(reservation.getTicketBooked(), 1);
	}
	
	@Test
	void testGetDeletedTicket() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setDeletedTicket(1);
		assertEquals(reservation.getDeletedTicket(), 1);
	}

	@Test
	void testSetDeletedTicket() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		reservation.setDeletedTicket(1);
		assertEquals(reservation.getDeletedTicket(), 1);
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
	
	@Test
	void testRemoveEmptyPassengerRow() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		reservation = reservationMock.creteReservationMock(reservation);
		
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		passenger = passengerMock.createPassengerMock(passenger);
		
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(6);
		reservation.addInPassengerInformationList(passengerInformationList, passenger);
		reservation.setPassengerInformation(passengerInformationList);
		reservation.removeEmptyPassengerRow(reservation);
		
		assertEquals(reservation.getPassengerInformation().size(), 1);
	}

	@Test
	void testIsPassengerListEmpty() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		reservation = reservationMock.creteReservationMock(reservation);
		
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		passenger = passengerMock.createPassengerMock(passenger);
		
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
		reservation.setPassengerInformation(passengerInformationList);
		assertTrue(reservation.isPassengerListEmpty(reservation));
		
		reservation.addInPassengerInformationList(passengerInformationList, passenger);
		reservation.setPassengerInformation(passengerInformationList);
		assertFalse(reservation.isPassengerListEmpty(reservation));
	}
	
	@Test
	void testValidateReservation() {
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
		reservation = reservationMock.creteReservationMock(reservation);
		
		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
		reservation.setPassengerInformation(passengerInformationList);
		assertEquals(reservation.validateReservation(reservation), ReservationInformationErrorCodes.emptyPassengerList);
		
		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		
		IPassengerInformation passengerWithNegativeAge = reservationAbstractFactory.createNewPassengerInformation();
		reservation.addInPassengerInformationList(passengerInformationList, passengerMock.createPassengerMockAgeNegative(passengerWithNegativeAge));
		reservation.setPassengerInformation(passengerInformationList);
		
		IPassengerInformation passengerWithZeroAge = reservationAbstractFactory.createNewPassengerInformation();
		reservation.addInPassengerInformationList(passengerInformationList, passengerMock.createPassengerMockAgeZero(passengerWithZeroAge));
		reservation.setPassengerInformation(passengerInformationList);
		
		IPassengerInformation passengerWithHugeAge = reservationAbstractFactory.createNewPassengerInformation();
		reservation.addInPassengerInformationList(passengerInformationList, passengerMock.createPassengerMockAgeHuge(passengerWithHugeAge));
		reservation.setPassengerInformation(passengerInformationList);
		
		IPassengerInformation passengerWithFirstNameEmpty = reservationAbstractFactory.createNewPassengerInformation();
		reservation.addInPassengerInformationList(passengerInformationList, passengerMock.createPassengerMockFirstNameEmpty(passengerWithFirstNameEmpty));
		reservation.setPassengerInformation(passengerInformationList);
		
		IPassengerInformation passengerWithLastNameEmpty = reservationAbstractFactory.createNewPassengerInformation();
		reservation.addInPassengerInformationList(passengerInformationList, passengerMock.createPassengerMockLastNameEmpty(passengerWithLastNameEmpty));
		reservation.setPassengerInformation(passengerInformationList);
		
		IPassengerInformation passengerWithGenderEmpty = reservationAbstractFactory.createNewPassengerInformation();
		reservation.addInPassengerInformationList(passengerInformationList, passengerMock.createPassengerMockGenderEmpty(passengerWithGenderEmpty));
		reservation.setPassengerInformation(passengerInformationList);
		
		IPassengerInformation passengerWithBerthPreferenceEmpty = reservationAbstractFactory.createNewPassengerInformation();
		reservation.addInPassengerInformationList(passengerInformationList, passengerMock.createPassengerMockBerthPreferenceEmpty(passengerWithBerthPreferenceEmpty));
		reservation.setPassengerInformation(passengerInformationList);
		
		String errorString = PassengerInformationErrorCodes.ageInvalid + 
				PassengerInformationErrorCodes.ageInvalid + 
				PassengerInformationErrorCodes.ageInvalid + 
				PassengerInformationErrorCodes.firstNameMissing + 
				PassengerInformationErrorCodes.lastNameMissing +
				PassengerInformationErrorCodes.genderMissing + 
				PassengerInformationErrorCodes.berthPreferenceMissing;
		
		assertEquals(errorString, reservation.validateReservation(reservation));
	}
}
