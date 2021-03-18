package com.project.setup;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
		IStation station = setupAbstractFactory.createNewStation();
		model.addAttribute(station);
		return "station/add_station";
	}

	@PostMapping(value = "/admin/station/save")
	public String saveStation(@Valid @ModelAttribute("station") IStation station, Model model) {
		
		boolean validOrNot = false;
		
		if(station.isStationNameValid()) {
			model.addAttribute("ErrorStationName" ,true );
			validOrNot = true;
		}
		 if(station.isStationCodeValid() ) {
			model.addAttribute("ErrorStationCode", true);
			validOrNot = true;
		}
		 if(station.isStationCityValid()) {
			model.addAttribute("ErrorStationCity" , true);
			validOrNot = true;
		}
		 if(station.isStationStateValid()) {
			model.addAttribute("ErrorStationState" , true );
			validOrNot = true;
		}
		 if(validOrNot) {
			return "station/add_station";
		}
		else {
			SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
			IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
			stationDAO.save(station);
			return "redirect:/admin/station/list";
		}

	}
	
	@ModelAttribute("station")
	public IStation getIStationModelObject(HttpServletRequest request) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStation station = setupAbstractFactory.createNewStation();
		return station;
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
