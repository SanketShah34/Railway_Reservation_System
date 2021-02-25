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
	public String ShowRoutePage(Model model) {
		List<Route> listOfRoute = routeService.ListOfRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
	}

	@GetMapping(value = "/route")
	public String ShowRoute(Model model) {
		List<Route> listOfRoute = routeService.ListOfRoute();
		model.addAttribute("listOfRoute", listOfRoute);
		return "route/route";
		
	}

	@GetMapping(value = "/route/add")
	public String ShowAddRoutePage(Model model) {
		List<Station> stations = stationService.ListOfStations();
		Route route = new Route();
		model.addAttribute(route);	
		model.addAttribute("listOfStations", stations);
		return "route/add_route";
	}

	@PostMapping(value = "/route/save")
	public String saveRoute(@Valid @ModelAttribute("route") Route route, BindingResult result) {
		System.out.println(route);
		if (result.hasErrors()) {
			System.out.println("in if");
			return "route/add_route";
		} else {
			System.out.println("in else");
			routeService.save(route);
			return "redirect:/route/list";
		}

	}

	@RequestMapping("/route/edit/{rid}")
	public String showEditRoutePage(@PathVariable(name = "rid") Integer rid, Model model) {
		List<Station> stations = stationService.ListOfStations();
		Route route = routeService.getRoute(rid);
		model.addAttribute(route);
		model.addAttribute("listOfStations", stations);
		return "route/edit_route";
	}

	@RequestMapping("/route/delete/{rid}")
	public String deleteRoute(@PathVariable(name = "rid") Integer rid, Model model) {
		routeService.deleteRoute(rid);
		return "redirect:/route/list";

	}
}
