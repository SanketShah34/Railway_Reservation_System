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
import com.project.logic.AvailableSeats;
import com.project.logic.findFare;
import com.project.reservation.IReservation;
import com.project.reservation.Reservation;
import com.project.reservation.ReservationAbstractFactory;
import com.project.setup.Station;
import com.project.setup.Train;
import com.project.setup.IStation;
import com.project.setup.IStationDAO;
import com.project.setup.SetupAbstractFactory;

@Controller
@ComponentScan("com.project.entity")
public class SearchTrainController {
	
	@Autowired
	com.project.service.SearchTrain searchTrainService;
	
	@Autowired
	findFare findfare;
	
	@Autowired
	AvailableSeats availbility;
	
	@GetMapping(value = "/user/home")
	public String showSearchTrainModel(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		SearchTrain searchTrain = new SearchTrain();
		List<Station> sourceStations = stationDAO.getAllStation();
		List<Station> destinationStation = stationDAO.getAllStation();
		model.addAttribute("listOfSourceStations", sourceStations);
		model.addAttribute("listOfDestinationStations", destinationStation);
		model.addAttribute(searchTrain); 
		
		return "searchTrain/searchTrain";
	}
	
	
	@PostMapping(value = "/user/home")
	public String SearchTrainModel(@Valid @ModelAttribute("searchTrain")  SearchTrain searchTrain ,  BindingResult result , Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		IReservation reservation = reservationAbstractFactory.createReservation();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		
		Station sourceStation = null;
		Station destinationStation = null;
		if (result.hasErrors()) {
			
			List<Station> sourceStations = stationDAO.getAllStation();
			List<Station> destinationStations = stationDAO.getAllStation();
			model.addAttribute("listOfSourceStations", sourceStations);
			model.addAttribute("listOfDestinationStations", destinationStations);
			return "searchTrain/searchTrain";
		} else {
			List<Train> trainList =  searchTrainService.searchTrains(searchTrain);
			
			if(trainList.size() <= 0) {
				model.addAttribute("noTrain" , true);
				return "searchTrain/listOfSearchTrain";
			}
			else {
				
				List<Station> listOfStation = stationDAO.getAllStation();
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
				List<Train> trainListWithFairCalculation = findfare.findFareofTrainjourney(trainList,searchTrain.getSourceStation() , searchTrain.getDestinationStation());
				
				//for seat avalibility algorithm
				List<Train> trainWithSeatAvalibility = availbility.findAvailableSeats(trainListWithFairCalculation , searchTrain , sourceStation.stationName, destinationStation.stationName); 
				model.addAttribute("listOfTrain", trainListWithFairCalculation);
				model.addAttribute("sourceStation",sourceStation);
				model.addAttribute("destinationStation",destinationStation);
				model.addAttribute("noTrain" , false);
				model.addAttribute("reservationInformation", reservation);
				System.out.println(trainList.size());
				return "searchTrain/listOfSearchTrain";
			}
		
		}
	}
	
}
