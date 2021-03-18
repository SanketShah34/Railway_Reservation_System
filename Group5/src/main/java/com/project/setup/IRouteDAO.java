package com.project.setup;

import java.util.List;

public interface IRouteDAO {
	public void saveRoute(IRoute route);
	
	public List<IRoute> getAllRoute();
	
	public IRoute getRoute(Integer routeId);
	
	public void deleteRoute(Integer routeId);
	
	public IRoute getRouteByStation(int sourcePoint ,int destinationPoint);
}
