package com.project.logic;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.dao.RouteDAO;
import com.project.dao.SeatAvailibilityDAO;
import com.project.entity.SearchTrain;
import com.project.entity.Station;
import com.project.entity.Train;

@Component
public class AvailableSeatsImpl implements AvailableSeats {
	
	@Autowired
	SeatAvailibilityDAO seatAvailibilityDAO;

	@Override
	public List<Train> findAvailableSeats(List<Train> trains, SearchTrain searchTrain, String sourceStationName,
			String destinationStationName) {
		System.out.println("in available seats");
		String sourceStation = sourceStationName;
		String destinationStation = destinationStationName;
		boolean forsourceStation = true;
		boolean fordestinationstation = true;
		for (int i = 0; i < trains.size(); i++) {
			System.out.println("i"+i);
			List<Integer> middleStationBetweenSourceStationAndDestinationStation = new ArrayList();
			List<Integer> totalStation = trains.get(i).totalStation;
			for (int j = 0; j < totalStation.size(); j++) {
				System.out.println("j"+j);
				if (forsourceStation) {
					if (totalStation.get(j) == Integer.parseInt(searchTrain.getSourceStation())) {
						System.out.println("for source station is false");
						forsourceStation = false;
					}
					continue;
				} else if (fordestinationstation) {
					System.out.println(" for middle station");
					if (totalStation.get(j) == Integer.parseInt(searchTrain.getDestinationStation())) {
						System.out.println(" for last one");
						fordestinationstation = false;
					}
					middleStationBetweenSourceStationAndDestinationStation.add(totalStation.get(j));
					continue;
				} else {
					System.out.println(" this is not called");
					
					break;
				}
			}
			
			seatAvalibility(trains.get(i) ,middleStationBetweenSourceStationAndDestinationStation , searchTrain );
			System.out.println("middle station between route " + middleStationBetweenSourceStationAndDestinationStation.size());
			
			
		}
		return trains;
	}
	
	
	public void seatAvalibility(Train train, List<Integer> middleStationBetweenrouteAndDestination , SearchTrain searchTrain){
		
		System.out.println(" total coaches"+train.getTotalCoaches());
		int totalSeatsInTrain = train.getTotalCoaches()*5;
		for(int  i = 0 ; i < middleStationBetweenrouteAndDestination.size() ; i++) {
			
			 int bookedOne = seatAvailibilityDAO.bookedTickets(searchTrain.getSourceStation().toString() , middleStationBetweenrouteAndDestination.get(i).toString() ,  train.getTrainId() , searchTrain.getDateofJourny());
			 //System.out.println("total booked seat is"+bookedOne);
			 totalSeatsInTrain -= bookedOne;
		}
		
		train.setAvailableSeat(totalSeatsInTrain);
		
	}
	

}
