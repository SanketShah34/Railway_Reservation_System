package com.project.calculation;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import com.project.lookup.ISearchTrain;
import com.project.lookup.LookupAbstractFactory;
import com.project.lookup.LookupAbstractFactoryTest;
import com.project.lookup.SearchTrainMock;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;
import com.project.setup.TrainMock;

class AvailableSeatsTest {

	@Test
	void testFindAvailableSeats() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		CalculationAbstractFactoryTest calculationAbstractFactoryTest = CalculationAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		train = trainMock.createTrainMock(train);
		ISeatAvailibilityDAO seatAvailibilityDAOMock = calculationAbstractFactoryTest.createSeatAvailibilityDAOMock();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		List<ITrain> listOfTrain = new ArrayList<ITrain>();
		listOfTrain.add(train);
		IAvailableSeats availableSeats = calculationAbstractFactory.createAvaliableSeats();
		availableSeats.findAvailableSeats(listOfTrain, searchTrain, seatAvailibilityDAOMock);
		assertEquals(listOfTrain.get(0).getAvailableSeat(), 138);
	}
	
	@Test
	void testfindAvailableSeatCountInSingleTrain() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		CalculationAbstractFactoryTest calculationAbstractFactoryTest = CalculationAbstractFactoryTest.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		train = trainMock.createTrainMock(train);
		ISeatAvailibilityDAO seatAvailibilityDAOMock = calculationAbstractFactoryTest.createSeatAvailibilityDAOMock();
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		IAvailableSeats availableSeats = calculationAbstractFactory.createAvaliableSeats();
		availableSeats.findAvailableSeatCountInSingleTrain(train, searchTrain, seatAvailibilityDAOMock);
		assertEquals(train.getAvailableSeat(), 138);
	}

	@Test
	void testListOfMiddleStation() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		train = trainMock.createTrainMock(train);
		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain);
		IAvailableSeats availableSeats = calculationAbstractFactory.createAvaliableSeats();
		List<Integer> middleStation = availableSeats.listOfMiddleStation(train, searchTrain);
		List<Integer> middleStationForTest = new ArrayList<Integer>();
		middleStationForTest.add(1);
		middleStationForTest.add(2);
		middleStationForTest.add(3);
		middleStationForTest.add(4);
		assertEquals(middleStation, middleStationForTest);
	}

	@Test
	void testFindMaximumSeatInSingleTrain() {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		CalculationAbstractFactoryTest calculationAbstractFactoryTest = CalculationAbstractFactoryTest.instance();
		ISeatAvailibilityDAO seatAvailibilityDAOMock = calculationAbstractFactoryTest.createSeatAvailibilityDAOMock();
		ITrain train = setupAbstractFactory.createNewTrain();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		train = trainMock.createTrainMock(train);
		IAvailableSeats availableSeats = calculationAbstractFactory.createAvaliableSeats();
		assertEquals(2,
				availableSeats.findMaximumSeatInSingleTrain(train, new Date(61202516585000L), seatAvailibilityDAOMock));
	}

}
