package com.project.setup;

import static org.junit.Assert.*;
import org.junit.jupiter.api.Test;


public class RouteTest {

	SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
	SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
	
	@Test
	public void testGetSource() {
		IRoute route = setupAbstractFactory.createNewRoute();
		IStation station = setupAbstractFactory.createNewStation();
		StationMock stationMock = setupAbstractFactoryTest.createStationMock();
		station = stationMock.createStationMock(station);
		route.setSource(station);
		assertEquals(station, route.getSource());
	}

	@Test
	public void testSetSource() {
		IRoute route = setupAbstractFactory.createNewRoute();
		IStation station = setupAbstractFactory.createNewStation();
		StationMock stationMock = setupAbstractFactoryTest.createStationMock();
		station = stationMock.createStationMock(station);
		route.setSource(station);
		assertEquals(station, route.getSource());
	}

	@Test
	public void testGetDestination() {
		IRoute route = setupAbstractFactory.createNewRoute();
		IStation station = setupAbstractFactory.createNewStation();
		StationMock stationMock = setupAbstractFactoryTest.createStationMock();
		station = stationMock.createStationMock(station);
		route.setDestination(station);
		assertEquals(station, route.getDestination());
	}

	@Test
	public void testSetDestination() {
		IRoute route = setupAbstractFactory.createNewRoute();
		IStation station = setupAbstractFactory.createNewStation();
		StationMock stationMock = setupAbstractFactoryTest.createStationMock();
		station = stationMock.createStationMock(station);
		route.setDestination(station);
		assertEquals(station, route.getDestination());
	}

	@Test
	public void testGetDistance() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setDistance(10.0);
		assertEquals(route.getDistance(), 10.0, 0.2);
	}

	@Test
	public void testSetDistance() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setDistance(10.0);
		assertEquals(route.getDistance(), 10.0, 0.2);
	}

	@Test
	public void testGetRouteId() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setRouteId(1);
		assertEquals(route.getRouteId(), 1);
	}

	@Test
	public void testSetRouteId() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setRouteId(1);
		assertEquals(route.getRouteId(), 1);
	}

	@Test
	public void testGetSourceId() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setSourceId(1);
		assertEquals(route.getSourceId(), 1);
	}

	@Test
	public void testSetSourceId() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setSourceId(1);
		assertEquals(route.getSourceId(), 1);
	}

	@Test
	public void testGetDestinationId() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setDestinationId(1);
		assertEquals(route.getDestinationId(), 1);
	}

	@Test
	public void testSetDestinationId() {
		IRoute route = setupAbstractFactory.createNewRoute();
		route.setDestinationId(1);
		assertEquals(route.getDestinationId(), 1);
	}

	@Test
	public void testIsRouteEntryValid() {
		IRoute route = setupAbstractFactory.createNewRoute();
		RouteMock routeMock = setupAbstractFactoryTest.createRouteMock();
		
		route = routeMock.createRouteMockWithSourceIdMissing(route);
		assertEquals(route.isRouteEntryValid(), RouteErrorCodes.sourceStationMissing);
		
		route = routeMock.createRouteMockWithDestinationIdMissing(route);
		assertEquals(route.isRouteEntryValid(), RouteErrorCodes.destinationStationMissing);
		
		route = routeMock.createRouteMockWithSourceIdAndDestinationIdSame(route);
		assertEquals(route.isRouteEntryValid(), RouteErrorCodes.sourceAndDestinationStationSame);
		
		route = routeMock.createRouteMockWithDistanceZero(route);
		assertEquals(route.isRouteEntryValid(), RouteErrorCodes.distanceNegativeOrZero);
		
		route = routeMock.createRouteMockWithDistanceNegative(route);
		assertEquals(route.isRouteEntryValid(), RouteErrorCodes.distanceNegativeOrZero);
		
		route = routeMock.createRouteMock(route);
		assertEquals(route.isRouteEntryValid(), "");
	}

}
