package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.IRouteDAO;
import com.project.entity.Route;

@Service
public class RouteService implements IRouteService{

	@Autowired
	IRouteDAO routeDAO;
	
	@Override
	public void save(Route route) {
		// TODO Auto-generated method stub
		System.out.println(route);
		routeDAO.save(route);
	}

	@Override
	public List<Route> ListOfRoute() {
		// TODO Auto-generated method stub
		return routeDAO.getAllRoute();
	}

	@Override
	public Route getRoute(Integer rid) {
		// TODO Auto-generated method stub
		return routeDAO.getRoute(rid);
	}

	@Override
	public void deleteRoute(Integer rid) {
		// TODO Auto-generated method stub
		routeDAO.deleteRoute(rid);
	}

}
