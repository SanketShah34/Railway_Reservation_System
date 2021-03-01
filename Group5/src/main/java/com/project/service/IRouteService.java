package com.project.service;

import java.util.List;
import com.project.entity.Route;

public interface IRouteService {
	public void save(Route route);
	
	public List<Route> ListOfRoute();
	
	public Route getRoute(Integer rid);
	
	public void deleteRoute(Integer rid);
}
