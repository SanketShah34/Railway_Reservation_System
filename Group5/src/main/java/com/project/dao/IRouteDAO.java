package com.project.dao;

import java.util.List;
import com.project.entity.Route;

public interface IRouteDAO {
	public void save(Route route);
	
	public List<Route> getAllRoute();
	
	public Route getRoute(Integer rId);
	
	public void deleteRoute(Integer rId);
	
	public Route  getrouteByStation(int station1 ,int station2);
}
