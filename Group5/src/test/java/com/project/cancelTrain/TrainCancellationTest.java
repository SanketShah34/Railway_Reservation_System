package com.project.cancelTrain;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import com.project.lookup.LookupAbstractFactoryTest;
import com.project.lookup.SearchTrainDAOMock;
import com.project.lookup.SeatAvailibilityDAOMock;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.reservation.PassengerMock;
import com.project.reservation.ReservationAbstractFactory;
import com.project.reservation.ReservationAbstractFactoryTest;
import com.project.reservation.ReservationMock;
import com.project.setup.RouteDAOMock;
import com.project.setup.SetupAbstractFactoryTest;

class TrainCancellationTest {

//	@Test
//	void testRescheduleOnWeekDays() {
//		CancelTrainAbstractFactory cancelTrainAbstractFactory = CancelTrainAbstractFactory.instance();
//		LookupAbstractFactoryTest lookupConcreteFactoryTest = LookupAbstractFactoryTest.instance();
//		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
//		ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
//		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance(); 
//		
//		ITrainCancellation trainCancellation = cancelTrainAbstractFactory.createNewTrainCancellation();
//		IReservation reservation = reservationAbstractFactory.createNewReservation();
//		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
//		reservation = reservationMock.creteReservationMock(reservation);
//		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
//		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
//		passenger = passengerMock.createPassengerMock(passenger);
//		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
//		reservation.addInPassengerInformationList(passengerInformationList, passenger);
//		reservation.setPassengerInformation(passengerInformationList);
//		SearchTrainDAOMock searchTrainDAOMock = lookupConcreteFactoryTest.createSearchTrainDAOMock();
//		RouteDAOMock routeDAOMock = setupAbstractFactoryTest.createRouteDAOMock();
//		SeatAvailibilityDAOMock seatAvailabilityDAOMock = lookupConcreteFactoryTest.createSeatAvailibilityDAOMock();
//		
//		trainCancellation.rescheduleOnWeekDays(reservation, searchTrainDAOMock, routeDAOMock, seatAvailabilityDAOMock);
//	}
//
//	@Test
//	void testRescheduleOnWeekEnds() {
//		CancelTrainAbstractFactory cancelTrainAbstractFactory = CancelTrainAbstractFactory.instance();
//		LookupAbstractFactoryTest lookupConcreteFactoryTest = LookupAbstractFactoryTest.instance();
//		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
//		ReservationAbstractFactoryTest reservationAbstractFactoryTest = ReservationAbstractFactoryTest.instance();
//		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance(); 
//		
//		ITrainCancellation trainCancellation = cancelTrainAbstractFactory.createNewTrainCancellation();
//		IReservation reservation = reservationAbstractFactory.createNewReservation();
//		ReservationMock reservationMock = reservationAbstractFactoryTest.createReservationMock();
//		reservation = reservationMock.creteReservationMock(reservation);
//		IPassengerInformation passenger = reservationAbstractFactory.createNewPassengerInformation();
//		PassengerMock passengerMock = reservationAbstractFactoryTest.createPassengerMock();
//		passenger = passengerMock.createPassengerMock(passenger);
//		List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
//		reservation.addInPassengerInformationList(passengerInformationList, passenger);
//		reservation.setPassengerInformation(passengerInformationList);
//		SearchTrainDAOMock searchTrainDAOMock = lookupConcreteFactoryTest.createSearchTrainDAOMock();
//		RouteDAOMock routeDAOMock = setupAbstractFactoryTest.createRouteDAOMock();
//		SeatAvailibilityDAOMock seatAvailabilityDAOMock = lookupConcreteFactoryTest.createSeatAvailibilityDAOMock();
//		
//		trainCancellation.rescheduleOnWeekEnds(reservation, searchTrainDAOMock, routeDAOMock, seatAvailabilityDAOMock);
//	}
//
//	@Test
//	void testBookTicket() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testCancelTicket() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testFindDay() {
//		
//	}
//
//	@Test
//	void testGetNextDate() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	void testGetDateAfterAWeek() {
//		fail("Not yet implemented");
//	}

}
