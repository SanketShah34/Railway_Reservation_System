package com.project.service;

import java.util.List;
import com.project.entity.Route;

public interface IRouteService {
	public void save(Route route);
	
	public List<Route> listOfRoute();
	
	public Route getRoute(Integer rId);
	
	public void deleteRoute(Integer rId);
}
