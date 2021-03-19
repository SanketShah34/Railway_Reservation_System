package com.project.setup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RouteController {
	
	@ModelAttribute("route")
    public IRoute getIRouteModelObject(HttpServletRequest request) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRoute route = setupAbstractFactory.createRoute();
		return route ;
	}
	
	@GetMapping(value = "/admin/route/list")
	public String displayRouteList(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		List<IRoute> listOfRoute = routeDAO.getAllRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
	}

	@GetMapping(value = "/admin/route")
	public String displayRoute(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		List<IRoute> listOfRoute = routeDAO.getAllRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
		
	}

	@GetMapping(value = "/admin/route/add")
	public String displayAddRoute(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRoute route = setupAbstractFactory.createRoute();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> stations = stationDAO.getAllStation();
		model.addAttribute(route);	
		model.addAttribute("listOfStations", stations);
		return "route/addRoute";
	}
	
	@RequestMapping("/admin/route/edit/{routeId}")
	public String displayEditRoute(@PathVariable(name = "routeId") Integer routeId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> stations = stationDAO.getAllStation();
		IRoute route = routeDAO.getRoute(routeId);
		model.addAttribute(route);
		model.addAttribute("listOfStations", stations);
		return "route/editRoute";
	}

	@PostMapping(value = "/admin/route/new/save")
	public String saveNewRoute(@ModelAttribute("route") IRoute route, Model model) {
		String errorCodes = route.isRouteEntryValid(); 
		if (errorCodes.equals("")) {
			SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
			IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
			routeDAO.saveRoute(route);
			return "redirect:/admin/route/list";
		} else {
			model.addAttribute("errorCodes", errorCodes);
			return this.displayAddRoute(model);
		}
	}
	
	@PostMapping(value = "/admin/route/edit/save")
	public String saveEditRoute(@ModelAttribute("route") IRoute route, Model model) {
		String errorCodes = route.isRouteEntryValid();
		int routeId = route.getRouteId();
		if (errorCodes.equals("")) {
			SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
			IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
			routeDAO.saveRoute(route);
			return "redirect:/admin/route/list";
		} else {
			model.addAttribute("errorCodes", errorCodes);
			return this.displayEditRoute(routeId, model);
		}
	}

	@RequestMapping("/admin/route/delete/{routeId}")
	public String deleteRoute(@PathVariable(name = "routeId") Integer routeId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		routeDAO.deleteRoute(routeId);
		return "redirect:/admin/route/list";

	}
}
