package com.project.reservation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PassengerInformationTest {

	ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
	ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
	
	@Test
	void testGetAmountPaid() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setAmountPaid(100.0);
		assertEquals(passengerInformation.getAmountPaid(), 100.0, 0.2);
	}

	@Test
	void testSetAmountPaid() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setAmountPaid(100.0);
		assertEquals(passengerInformation.getAmountPaid(), 100.0, 0.2);
	}

	@Test
	void testGetPassengerInformationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setPassengerInformationId(1);
		assertEquals(passengerInformation.getPassengerInformationId(), 1);
	}

	@Test
	void testSetPassengerInformationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setPassengerInformationId(1);
		assertEquals(passengerInformation.getPassengerInformationId(), 1);
	}

	@Test
	void testGetReservationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setReservationId(1);
		assertEquals(passengerInformation.getReservationId(), 1);
	}

	@Test
	void testSetReservationId() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setReservationId(1);
		assertEquals(passengerInformation.getReservationId(), 1);
	}

	@Test
	void testGetFirstName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setFirstName("Halifax");
		assertEquals(passengerInformation.getFirstName(), "Halifax");
	}

	@Test
	void testSetFirstName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setFirstName("Halifax");
		assertEquals(passengerInformation.getFirstName(), "Halifax");
	}

	@Test
	void testGetLastName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setLastName("Halifax");
		assertEquals(passengerInformation.getLastName(), "Halifax");
	}

	@Test
	void testSetLastName() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setLastName("Halifax");
		assertEquals(passengerInformation.getLastName(), "Halifax");
	}

	@Test
	void testGetGender() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setGender("Female");
		assertEquals(passengerInformation.getGender(), "Female");
	}

	@Test
	void testSetGender() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setGender("Female");
		assertEquals(passengerInformation.getGender(), "Female");
	}

	@Test
	void testGetAge() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setAge(62);
		assertEquals(passengerInformation.getAge(), 62);
	}

	@Test
	void testSetAge() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setAge(62);
		assertEquals(passengerInformation.getAge(), 62);
	}

	@Test
	void testGetBerthPreference() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setBerthPreference("Lower Berth");
		assertEquals(passengerInformation.getBerthPreference(), "Lower Berth");
	}

	@Test
	void testSetBerthPreference() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setBerthPreference("Lower Berth");
		assertEquals(passengerInformation.getBerthPreference(), "Lower Berth");
	}

	@Test
	void testGetSeatNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setSeatNumber(1);
		assertEquals(passengerInformation.getSeatNumber(), 1);
	}

	@Test
	void testSetSeatNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setSeatNumber(1);
		assertEquals(passengerInformation.getSeatNumber(), 1);
	}

	@Test
	void testGetCoachNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setCoachNumber("A1");
		assertEquals(passengerInformation.getCoachNumber(), "A1");
	}

	@Test
	void testSetCoachNumber() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		passengerInformation.setCoachNumber("A1");
		assertEquals(passengerInformation.getCoachNumber(), "A1");
	}

	@Test
	void testIsPassengerInformationValid() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		
		passengerInformation = passengerMock.createPassengerMock(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), "");
		
		passengerInformation = passengerMock.createPassengerMockAgeNegative(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.ageInvalid);
		
		passengerInformation = passengerMock.createPassengerMockAgeZero(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.ageInvalid);
		
		passengerInformation = passengerMock.createPassengerMockAgeHuge(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.ageInvalid);
		
		passengerInformation = passengerMock.createPassengerMockFirstNameEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.firstNameMissing);
		
		passengerInformation = passengerMock.createPassengerMockLastNameEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.lastNameMissing);
		
		passengerInformation = passengerMock.createPassengerMockGenderEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.genderMissing);
		
		passengerInformation = passengerMock.createPassengerMockBerthPreferenceEmpty(passengerInformation);
		assertEquals(passengerInformation.isPassengerInformationValid(), PassengerInformationErrorCodes.berthPreferenceMissing);
	}

	@Test
	void testIsRowNonEmpty() {
		IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
		
		passengerInformation = passengerMock.createPassengerMock(passengerInformation);
		passengerInformation.setFirstName("");
		passengerInformation.setLastName("");
		assertFalse(passengerInformation.isRowNonEmpty());
	
		passengerInformation = passengerMock.createPassengerMockFirstNameEmpty(passengerInformation);
		assertTrue(passengerInformation.isRowNonEmpty());
		
		passengerInformation = passengerMock.createPassengerMockLastNameEmpty(passengerInformation);
		assertTrue(passengerInformation.isRowNonEmpty());
		
	}
}
