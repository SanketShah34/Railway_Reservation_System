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
		System.out.println(route);
		routeDAO.save(route);
	}

	@Override
	public List<Route> listOfRoute() {
		return routeDAO.getAllRoute();
	}

	@Override
	public Route getRoute(Integer rId) {
		return routeDAO.getRoute(rId);
	}

	@Override
	public void deleteRoute(Integer rId) {
		routeDAO.deleteRoute(rId);
	}

}
