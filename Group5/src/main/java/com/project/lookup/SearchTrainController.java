package com.project.lookup;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.project.calculation.CalculationAbstractFactory;
import com.project.calculation.IAvailableSeats;
import com.project.calculation.IFindFare;
import com.project.calculation.ISeatAvailibilityDAO;
import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;
import com.project.setup.IRouteDAO;
import com.project.setup.IStation;
import com.project.setup.IStationDAO;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

@Controller
public class SearchTrainController {
	
	
	@GetMapping(value = "/user/home")
	public String showSearchTrainModel(Model model) {
		
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		
		IStationDAO stationDAO = setupAbstractFactory.createNewStationDAO();
		ISearchTrain searchTrain =lookupAbstractFactory.createNewSearchTrain();
		
		List<IStation> sourceStations = stationDAO.getAllStation();
		List<IStation> destinationStation = stationDAO.getAllStation();
		model.addAttribute("listOfSourceStations", sourceStations);
		model.addAttribute("listOfDestinationStations", destinationStation);
		model.addAttribute(searchTrain); 
		
		return "searchTrain/searchTrain";
	}
	
	
	@PostMapping(value = "/user/home")
	public String SearchTrainModel(@Valid @ModelAttribute("searchTrain")  ISearchTrain searchTrain , Model model) {
		
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		ISeatAvailibilityDAO seatAvaillibilityDAO = calculationAbstractFactory.createNewSeatAvailibilityDAO();
		
		IReservation reservation = reservationAbstractFactory.createReservation();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		ISearchTrainDAO searchTrainDAO = lookupAbstractFactory.createSearchTrainDAO();
		IFindFare findFair = calculationAbstractFactory.createFindFair();
		IAvailableSeats availableSeats = calculationAbstractFactory.createAvaliableSeats();
		
		IStation sourceStation = null;
		IStation destinationStation = null;
		
		boolean hasError = false;
		
		if(searchTrain.issourceStationAndDestinationStationSame(searchTrain.getSourceStation(), searchTrain.getDestinationStation())) {
			System.out.println("in if condition");
			hasError = true;
			model.addAttribute("stationError" , "true");
		}
		if (hasError) 
		{
			List<IStation> sourceStations = stationDAO.getAllStation();
			List<IStation> destinationStations = stationDAO.getAllStation();
			model.addAttribute("listOfSourceStations", sourceStations);
			model.addAttribute("listOfDestinationStations", destinationStations);
			return "searchTrain/searchTrain";
		} 
		else 
		{
			List<ITrain> trainList =  searchTrainDAO.searchTrains(searchTrain);
			
			if(trainList.size() <= 0) {
				model.addAttribute("noTrain" , true);
				return "searchTrain/listOfSearchTrain";
			}
			else {
				
				List<IStation> listOfStation = stationDAO.getAllStation();
				for(IStation station : listOfStation) {
					if(station.getStationId() == Integer.parseInt(searchTrain.getSourceStation()) ) {
						sourceStation =  station;
					}
					else if(station.getStationId() == Integer.parseInt(searchTrain.getDestinationStation())) {
								destinationStation = station;
					}
				}
				
				
				//for fair calculation
				IRouteDAO routeDAO = setupAbstractFactory.createNewRouteDAO();
				List<ITrain> trainListWithFairCalculation = findFair.findFareofTrainJourney(trainList,searchTrain.getSourceStation() , searchTrain.getDestinationStation(), routeDAO);
				
				//for seat avalibility algorithm
				availableSeats.findAvailableSeats(trainListWithFairCalculation , searchTrain , sourceStation.getStationName() , destinationStation.getStationName() , seatAvaillibilityDAO); 
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
	
	@ModelAttribute("searchTrain")
	public ISearchTrain getISearchStationModelObject(HttpServletRequest request) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		return searchTrain;
	}
	
}
