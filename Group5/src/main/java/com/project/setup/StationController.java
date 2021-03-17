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
public class StationController {

	@GetMapping(value = "/admin/station/list")
	public String showStationPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<Station> listOfStation = stationDAO.getAllStation();
		model.addAttribute("listOfStation", listOfStation);
		return "station/station";
	}

	@GetMapping(value = "/admin/station")
	public String showStation(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<Station> listOfStation = stationDAO.getAllStation();
		model.addAttribute("listOfStation", listOfStation);
		return "station/station";
	}

	@GetMapping(value = "/admin/station/add")
	public String showAddStationPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStation station = setupAbstractFactory.createStation();
		model.addAttribute(station);
		return "station/add_station";
	}

	@PostMapping(value = "/admin/station/save")
	public String saveStation(@Valid @ModelAttribute("station") IStation station, BindingResult result) {

		if (result.hasErrors()) {
			return "station/add_station";
		} else {
			SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
			IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
			stationDAO.save(station);
			return "redirect:/admin/station/list";
		}

	}

	@RequestMapping("/admin/station/edit/{sId}")
	public String showEditStationPage(@PathVariable(name = "sId") Integer sId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		IStation station = stationDAO.getStation(sId);
		model.addAttribute(station);
		return "station/edit_station";
	}

	@RequestMapping("/admin/station/delete/{sId}")
	public String deleteStation(@PathVariable(name = "sId") Integer sId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		stationDAO.delete(sId);
		return "redirect:/admin/station/list";

	}

}
