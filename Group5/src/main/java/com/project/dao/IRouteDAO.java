package com.project.dao;

import java.util.List;
import com.project.entity.Route;

public interface IRouteDAO {
	public void save(Route route);
	
	public List<Route> getAllRoute();
	
	public Route getRoute(Integer rid);
	
	public void deleteRoute(Integer rid);
}
