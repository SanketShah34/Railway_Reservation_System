package com.project.setup;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.jupiter.api.Test;

public class TrainTest {

	SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
	SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
	
	@Test
	public void testGetTrainId() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainId(1);
		assertEquals(1, train.getTrainId());
	}

	@Test
	public void testSetTrainId() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainId(1);
		assertEquals(1, train.getTrainId());
	}
	
	@Test
	public void testGetTrainCode() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainCode(1);
		assertEquals(1, train.getTrainCode());
	}

	@Test
	public void testSetTrainCode() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainCode(1);
		assertEquals(1, train.getTrainCode());
	}
	
	@Test
	public void testGetTrainName() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainName("Halifax Express");
		assertEquals("Halifax Express", train.getTrainName());
	}

	@Test
	public void testSetTrainName() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainName("Halifax Express");
		assertEquals("Halifax Express", train.getTrainName());
	}
	
	@Test
	public void testGetTrainType() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainType("AC Seater");
		assertEquals("AC Seater", train.getTrainType());
	}

	@Test
	public void testSetTrainType() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTrainType("AC Seater");
		assertEquals("AC Seater", train.getTrainType());
	}
	
	@Test
	public void testGetDays() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setDays("Mon");
		assertEquals("Mon", train.getDays());
	}

	@Test
	public void testSetDays() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setDays("Mon");
		assertEquals("Mon", train.getDays());
	}
	
	@Test
	public void testGetDepartureTime() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setDepartureTime("12:30");
		assertEquals("12:30", train.getDepartureTime());
	}

	@Test
	public void testSetDepartureTime() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setDepartureTime("12:30");
		assertEquals("12:30", train.getDepartureTime());
	}
	
	@Test
	public void testGetTotalCoaches() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTotalCoaches(11);
		assertEquals(11, train.getTotalCoaches());
	}

	@Test
	public void testSetTotalCoaches() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTotalCoaches(11);
		assertEquals(11, train.getTotalCoaches());
	}
	
	@Test
	public void testGetStartStation() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setStartStation("A1");
		assertEquals("A1", train.getStartStation());
	}

	@Test
	public void testSetStartStation() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setStartStation("A1");
		assertEquals("A1", train.getStartStation());
	}
	
	@Test
	public void testGetMiddleStations() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setMiddleStations("A2");
		assertEquals("A2", train.getMiddleStations());
	}

	@Test
	public void testSetMiddleStations() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setMiddleStations("A2");
		assertEquals("A2", train.getMiddleStations());
	}
	
	@Test
	public void testGetEndStation() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setEndStation("A1");
		assertEquals("A1", train.getEndStation());
	}

	@Test
	public void testSetEndStation() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setEndStation("A1");
		assertEquals("A1", train.getEndStation());
	}
	
	@Test
	public void testGetTotalStation() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTotalStation(null);
		assertEquals(null, train.getTotalStation());
	}

	@Test
	public void testSetTotalStation() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTotalStation(null);
		assertEquals(null, train.getTotalStation());
	}
	
	@Test
	public void testGetFare() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setFare(10.0);
		assertNotNull(train.getFare());
	}

	@Test
	public void testSetFare() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setFare(10.0);
		assertNotNull(train.getFare());
	}
	
	@Test
	public void testGetPickUPTime() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setPickUPTime("10");
		assertEquals("10", train.getPickUPTime());
	}

	@Test
	public void testSetPickUPTime() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setPickUPTime("10");
		assertEquals("10", train.getPickUPTime());
	}
	
	@Test
	public void testGetDropTime() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setDropTime("12");
		assertEquals("12", train.getDropTime());
	}

	@Test
	public void testSetDropTime() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setDropTime("12");
		assertEquals("12", train.getDropTime());
	}
	
	@Test
	public void testGetAvailableSeat() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setAvailableSeat(50);
		assertEquals(50, train.getAvailableSeat());
	}

	@Test
	public void testSetAvailableSeat() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setAvailableSeat(50);
		assertEquals(50, train.getAvailableSeat());
	}
	
	@Test
	public void testGetTotalDistance() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTotalDistance(12);
		assertEquals(12, train.getTotalDistance());
	}

	@Test
	public void testSetTotalDistance() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setTotalDistance(12);
		assertEquals(12, train.getTotalDistance());
	}
}
