package com.project.calculation;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.setup.ITrain;
import com.project.setup.RouteDAOMock;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;

class FindFareTest {

	CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
	
	@Test
	void testFindFareofTrainJourney() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		
		List<ITrain> trainList = new ArrayList<ITrain>();
		ITrain train = setupAbstractFactory.createNewTrain();
//		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
//		train = trainMock.createTrainMock(train);
		trainList.add(train);
		
		RouteDAOMock routeDAOMock = setupAbstractFactoryTest.createRouteDAOMock();
		IFindFare findFare = calculationAbstractFactory.createNewFindFair();
		findFare.findFareofTrainJourney(trainList, "Halifax", "Toronto", routeDAOMock);
		
	}

	@Test
	void testTimeCounter() {
		IFindFare findFare = calculationAbstractFactory.createNewFindFair();
		assertEquals(findFare.timeCounter("0:00"), 0);
		assertEquals(findFare.timeCounter("0:23"), 23);
		assertEquals(findFare.timeCounter("0:01"), 1);
		assertEquals(findFare.timeCounter("1:00"), 60);
		assertEquals(findFare.timeCounter("1:05"), 65);
		assertEquals(findFare.timeCounter("1:10"), 70);
	}

	@Test
	void testMinuteToHoursConverter() {
		IFindFare findFare = calculationAbstractFactory.createNewFindFair();
		assertEquals(findFare.minuteToHoursConverter(0), "0:00");
		assertEquals(findFare.minuteToHoursConverter(23), "0:23");
		assertEquals(findFare.minuteToHoursConverter(1), "0:01");
		assertEquals(findFare.minuteToHoursConverter(60), "1:00");
		assertEquals(findFare.minuteToHoursConverter(65), "1:05");
		assertEquals(findFare.minuteToHoursConverter(70), "1:10");
	}

	@Test
	void testMinuteFormater() {
		IFindFare findFare = calculationAbstractFactory.createNewFindFair();
		assertEquals(findFare.minuteFormater(1), "01");
		assertEquals(findFare.minuteFormater(0), "00");
		assertEquals(findFare.minuteFormater(10), "10");
	}

	@Test
	void testCalculateFareByDistance() {
		IFindFare findFare = calculationAbstractFactory.createNewFindFair();
		assertEquals(findFare.calculateFareByDistance(0, 100), 0.0);
		assertEquals(findFare.calculateFareByDistance(50, 100), 100.0);
		assertEquals(findFare.calculateFareByDistance(100, 100), 80.0);
		assertEquals(findFare.calculateFareByDistance(120, 100), 80.0);
	}

	@Test
	void testCalculateFareByTrainType() {
		IFindFare findFare = calculationAbstractFactory.createNewFindFair();
		try {
			assertEquals(findFare.calculateFareByTrainType(100, "Non AC Sleeper"), 300.0);	
			assertEquals(findFare.calculateFareByTrainType(100, "AC Sleeper"), 400.0);
			assertEquals(findFare.calculateFareByTrainType(100, "Non AC Seater"), 200.0);
			assertEquals(findFare.calculateFareByTrainType(100, "AC Seater"), 300.0);
			assertEquals(findFare.calculateFareByTrainType(100, "Invaid"), 300.0);
		} catch(Exception exception) {
			assertEquals(exception.getMessage(), "Invalid Train Type");
		}
		
	}

	@Test
	void testCalculateFareByAge() {
		IFindFare findFare = calculationAbstractFactory.createNewFindFair();
		assertEquals(findFare.calculateFareByAge(100.0, 0), 0.0);
		assertEquals(findFare.calculateFareByAge(100.0, 4), 50.0);
		assertEquals(findFare.calculateFareByAge(100.0, 5), 100.0);
		assertEquals(findFare.calculateFareByAge(100.0, 20), 100.0);
		assertEquals(findFare.calculateFareByAge(100.0, 60), 70.0);
		assertEquals(findFare.calculateFareByAge(100.0, 62), 70.0);
	}

}
