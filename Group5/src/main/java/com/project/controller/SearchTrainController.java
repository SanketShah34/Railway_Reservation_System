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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.entity.SearchTrain;
import com.project.entity.Station;
import com.project.entity.Train;
import com.project.logic.IfindFair;
import com.project.logic.findFair;
import com.project.reservation.Reservation;
import com.project.service.StationService;

@Controller
@ComponentScan("com.project.entity")
public class SearchTrainController {
	
	@Autowired
	StationService stationService;
	
	@Autowired
	com.project.service.SearchTrain searchTrainService;
	
	@Autowired
	IfindFair findfair;
	
	
	
	@GetMapping(value = "/user/home")
	public String showSearchTrainModel(Model model) {
		SearchTrain searchTrain = new SearchTrain();
		
		List<Station> sourceStations = stationService.listOfStations();
		List<Station> destinationStation = stationService.listOfStations();
		model.addAttribute("listOfSourceStations", sourceStations);
		model.addAttribute("listOfDestinationStations", destinationStation);
		model.addAttribute(searchTrain); 
		
		return "searchTrain/searchTrain";
	}
	
	
	@RequestMapping(value = "/user/searchTrain")
	public String SearchTrainModel(@Valid @ModelAttribute("searchTrain")  SearchTrain searchTrain ,  BindingResult result , Model model) {
		
//		System.out.println("____"+searchTrain.getSourceStation());
//		System.out.println("____"+searchTrain.getDestinationStation());
//		System.out.println("____"+searchTrain.getTrainType());
//		System.out.println("____"+searchTrain.getDateofJourny());
		Station sourceStation = null;
		Station destinationStation = null;
		if (result.hasErrors()) {
			System.out.println("in if");
			return "searchTrain/searchTrain";
		} else {
			System.out.println("in else");
			List<Train> trainList =  searchTrainService.searchTrains(searchTrain);
			
			if(trainList.size() <= 0) {
				System.out.println("in if ");
			}
			else {
				
				List<Station> listOfStation = stationService.listOfStations();
				for(Station station : listOfStation) {
					if(station.getSId() == Integer.parseInt(searchTrain.getSourceStation()) ) {
						sourceStation =  station;
						System.out.println("-------"+station.getStationName());
					}
					else if(station.getSId() == Integer.parseInt(searchTrain.getDestinationStation())) {
								destinationStation = station;
					}
				}
				
				
				System.out.println("in else");
				List<Train> trainListWithFairCalculation =findfair.findFairofTrainjourney(trainList,searchTrain.getSourceStation() , searchTrain.getDestinationStation());
				model.addAttribute("listOfTrain", trainListWithFairCalculation);
				model.addAttribute("sourceStation",sourceStation);
				model.addAttribute("destinationStation",destinationStation);
				model.addAttribute("reservationInformation", new Reservation());
				
			}
			
			
			
			
			System.out.println(trainList.size());
			return "searchTrain/listOfSearchTrain";
		}
	}
	
}
