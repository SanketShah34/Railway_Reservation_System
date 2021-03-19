package com.project.setup;

import java.util.List;

public class RouteDAOMock implements IRouteDAO{

	@Override
	public void saveRoute(IRoute route) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<IRoute> getAllRoute() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IRoute getRoute(Integer routeId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteRoute(Integer routeId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IRoute getRouteByStation(int sourcePoint, int destinationPoint) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		IRoute route = setupAbstractFactory.createNewRoute();
		RouteMock routeMock = setupAbstractFactoryTest.createRouteMock();
		route = routeMock.createRouteMock(route);
		return route;
	}

}
