package com.project.lookup;

import static org.junit.Assert.assertEquals;
import java.util.Date;
import org.junit.jupiter.api.Test;

class SearchTrainTest {
	
	LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
	LookupAbstractFactoryTest lookupAbstractFactoryTest = LookupAbstractFactoryTest.instance();
	
	@Test
	void testGetSourceStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setSourceStation("Surat");
		assertEquals("Surat", searchTrain.getSourceStation());
	}

	@Test
	void testSetSourceStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setSourceStation("Surat");
		assertEquals("Surat", searchTrain.getSourceStation());
	}

	@Test
	void testGetDestinationStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setDestinationStation("Ahembdabad");
		assertEquals("Ahembdabad", searchTrain.getDestinationStation());

	}

	@Test
	void testSetDestinationStation() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setDestinationStation("Ahembdabad");
		assertEquals("Ahembdabad", searchTrain.getDestinationStation());
	}

	@Test
	void testGetDateofJourny() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setDateofJourny(new Date());
		assertEquals(new Date(), searchTrain.getDateofJourny());
	}

	@Test
	void testSetDateofJourny() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setDateofJourny(new Date());
		assertEquals(new Date(), searchTrain.getDateofJourny());
	}

	@Test
	void testGetTrainType() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setTrainType("AC SLEEPER");;
		assertEquals("AC SLEEPER", searchTrain.getTrainType());
	}

	@Test
	void testSetTrainType() {
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		searchTrain.setTrainType("AC SLEEPER");;
		assertEquals("AC SLEEPER", searchTrain.getTrainType());
	}

	@Test
	void testIssourceStationAndDestinationStationSame() {
		ISearchTrain  searchTrain = lookupAbstractFactory.createNewSearchTrain();
	    assertEquals(true, searchTrain.issourceStationAndDestinationStationSame("1","1"));
	    assertEquals(false, searchTrain.issourceStationAndDestinationStationSame("1","3"));
		
	}

}
