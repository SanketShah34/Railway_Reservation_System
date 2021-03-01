package com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.entity.Route;
import com.project.entity.Station;
import com.project.service.RouteService;
import com.project.service.StationService;

@Controller
@ComponentScan("com.project.entity")
public class RouteController {

	@Autowired
	RouteService routeService;
	
	@Autowired
	StationService stationService;
	
	@GetMapping(value = "/route/list")
	public String showRoutePage(Model model) {
		List<Route> listOfRoute = routeService.listOfRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
	}

	@GetMapping(value = "/route")
	public String showRoute(Model model) {
		List<Route> listOfRoute = routeService.listOfRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
		
	}

	@GetMapping(value = "/route/add")
	public String showAddRoutePage(Model model) {
		List<Station> stations = stationService.listOfStations();
		Route route = new Route();
		model.addAttribute(route);	
		model.addAttribute("listOfStations", stations);
		return "route/addRoute";
	}

	@PostMapping(value = "/route/save")
	public String saveRoute(@Valid @ModelAttribute("route") Route route, BindingResult result) {
		if (result.hasErrors()) {
			return "route/addRoute";
		} else {
			routeService.save(route);
			return "redirect:/route/list";
		}

	}

	@RequestMapping("/route/edit/{rId}")
	public String showEditRoutePage(@PathVariable(name = "rId") Integer rId, Model model) {
		List<Station> stations = stationService.listOfStations();
		Route route = routeService.getRoute(rId);
		model.addAttribute(route);
		model.addAttribute("listOfStations", stations);
		return "route/editRoute";
	}

	@RequestMapping("/route/delete/{rId}")
	public String deleteRoute(@PathVariable(name = "rId") Integer rId, Model model) {
		routeService.deleteRoute(rId);
		return "redirect:/route/list";

	}
}
