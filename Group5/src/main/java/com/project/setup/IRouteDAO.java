package com.project.setup;

import java.util.List;

public interface IRouteDAO {
	public void save(IRoute route);
	
	public List<Route> getAllRoute();
	
	public IRoute getRoute(Integer rId);
	
	public void deleteRoute(Integer rId);
	
	public IRoute  getrouteByStation(int station1 ,int station2);
}
