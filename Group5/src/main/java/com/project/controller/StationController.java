package com.project.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.entity.Station;
import com.project.service.StationService;

import lombok.val;

@Controller
@ComponentScan("com.project.entity")
public class StationController {

	@Autowired
	StationService stationService;

	@GetMapping(value = "/station/list")
	public String showStationPage(Model model) {
		List<Station> listOfStation = stationService.listOfStations();
		System.out.println("PPPP" + listOfStation.size());
		model.addAttribute("listOfStation", listOfStation);
		return "station/station";
	}

	@GetMapping(value = "/station")
	public String showStation(Model model) {
		List<Station> listOfStation = stationService.listOfStations();
		System.out.println("PPPP" + listOfStation.size());
		model.addAttribute("listOfStation", listOfStation);
		return "station/station";
	}

	@GetMapping(value = "/station/add")
	public String showAddStationPage(Model model) {
		Station station = new Station();
		model.addAttribute(station);
		return "station/add_station";
	}

	@PostMapping(value = "/station/save")
	public String saveStation(@Valid @ModelAttribute("station") Station station,BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("in if");
			return "station/add_station";
		} else {
			System.out.println("in else");
			stationService.save(station);
			return "redirect:/station/list";
		}

	}

	@RequestMapping("/station/edit/{sid}")
	public String showEditStationPage(@PathVariable(name = "sId") Integer sId, Model model) {

		Station station = stationService.getStation(sId);
		model.addAttribute(station);
		return "station/edit_station";
	}

	@RequestMapping("/station/delete/{sId}")
	public String deleteStation(@PathVariable(name = "sId") Integer sId, Model model) {
		stationService.deleteStation(sId);
		return "redirect:/station/list";

	}

}
