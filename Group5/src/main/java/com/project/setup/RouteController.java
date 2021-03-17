package com.project.setup;

import java.util.List;

import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class RouteController {
	
	@GetMapping(value = "/admin/route/list")
	public String showRoutePage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		List<Route> listOfRoute = routeDAO.getAllRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
	}

	@GetMapping(value = "/admin/route")
	public String showRoute(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		List<Route> listOfRoute = routeDAO.getAllRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
		
	}

	@GetMapping(value = "/admin/route/add")
	public String showAddRoutePage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRoute route = setupAbstractFactory.createRoute();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<Station> stations = stationDAO.getAllStation();
		model.addAttribute(route);	
		model.addAttribute("listOfStations", stations);
		return "route/addRoute";
	}

	@PostMapping(value = "/admin/route/save")
	public String saveRoute(@Valid @ModelAttribute("route") IRoute route, BindingResult result) {
		if (result.hasErrors()) {
			return "route/addRoute";
		} else {
			SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
			IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
			routeDAO.save(route);
			return "redirect:/admin/route/list";
		}

	}

	@RequestMapping("/admin/route/edit/{rId}")
	public String showEditRoutePage(@PathVariable(name = "rId") Integer rId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<Station> stations = stationDAO.getAllStation();
		IRoute route = routeDAO.getRoute(rId);
		model.addAttribute(route);
		model.addAttribute("listOfStations", stations);
		return "route/editRoute";
	}

	@RequestMapping("/admin/route/delete/{rId}")
	public String deleteRoute(@PathVariable(name = "rId") Integer rId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();
		routeDAO.deleteRoute(rId);
		return "redirect:/admin/route/list";

	}
}
