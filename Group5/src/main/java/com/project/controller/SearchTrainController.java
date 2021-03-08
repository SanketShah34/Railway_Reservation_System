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
import com.project.logic.AvailableSeats;
import com.project.logic.findFare;
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
	findFare findfare;
	
	@Autowired
	AvailableSeats availbility;
	
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
	
	
	@PostMapping(value = "/user/home")
	public String SearchTrainModel(@Valid @ModelAttribute("searchTrain")  SearchTrain searchTrain ,  BindingResult result , Model model) {
		
		Station sourceStation = null;
		Station destinationStation = null;
		if (result.hasErrors()) {
			
			List<Station> sourceStations = stationService.listOfStations();
			List<Station> destinationStations = stationService.listOfStations();
			model.addAttribute("listOfSourceStations", sourceStations);
			model.addAttribute("listOfDestinationStations", destinationStations);
			System.out.println("in if 1");
			return "searchTrain/searchTrain";
		} else {
			System.out.println("in else");
			List<Train> trainList =  searchTrainService.searchTrains(searchTrain);
			
			if(trainList.size() <= 0) {
				System.out.println("in if 2");
				model.addAttribute("noTrain" , true);
				return "searchTrain/listOfSearchTrain";
			}
			else {
				
				List<Station> listOfStation = stationService.listOfStations();
				for(Station station : listOfStation) {
					if(station.getSId() == Integer.parseInt(searchTrain.getSourceStation()) ) {
						sourceStation =  station;
					}
					else if(station.getSId() == Integer.parseInt(searchTrain.getDestinationStation())) {
								destinationStation = station;
					}
				}
				
				
				System.out.println("in else b");
				//for fair calculation
				List<Train> trainListWithFairCalculation =findfare.findFareofTrainjourney(trainList,searchTrain.getSourceStation() , searchTrain.getDestinationStation());
				
				//for seat avalibility algorithm
				List<Train> trainWithSeatAvalibility = availbility.findAvailableSeats(trainListWithFairCalculation , searchTrain , sourceStation, destinationStation); 
				model.addAttribute("listOfTrain", trainListWithFairCalculation);
				model.addAttribute("sourceStation",sourceStation);
				model.addAttribute("destinationStation",destinationStation);
				model.addAttribute("noTrain" , false);
				System.out.println(trainList.size());
				return "searchTrain/listOfSearchTrain";
			}
		
		}
	}
	
}
