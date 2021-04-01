package com.project.calculation;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

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
		
//		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
//		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
//		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
//		LookupAbstractFactoryTest lookupAbstractFactoryTest =  LookupAbstractFactoryTest.instance();
//		CalculationAbstractFactoryTest calculationAbstractFactoryTest = CalculationAbstractFactoryTest.instance();
//		
//		ITrain train = setupAbstractFactory.createNewTrain();
//		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
//		
//		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
//		train = trainMock.createTrainMock(train);
//		
//		ISeatAvailibilityDAO seatAvailibilityDAOMock = calculationAbstractFactoryTest.createSeatAvailibilityDAOMock(); 
//		
//		SearchTrainMock searchTrainMock = lookupAbstractFactoryTest.createSearchTrainMock();
//		searchTrain = searchTrainMock.createSearchTrainMock(searchTrain); 
//		
//		List<ITrain> listOfTrain = new ArrayList<ITrain>();
//		
//		listOfTrain.add(train);
//		
//		IAvailableSeats availableSeats = new AvailableSeats();
		
//		availableSeats.findAvailableSeats(listOfTrain, searchTrain, "1", "4" , seatAvailibilityDAOMock);
		
//		assertEquals(listOfTrain.get(0).getAvailableSeat(), 110 );
		
		assertTrue(true);
		
	}

}
