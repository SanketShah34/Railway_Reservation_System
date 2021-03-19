package com.project.calculation;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.project.setup.ITrain;
import com.project.lookup.ISearchTrain;

@Component
public class AvailableSeats implements IAvailableSeats {

	@Override
	public List < ITrain > findAvailableSeats(List<ITrain> trains, ISearchTrain searchTrain, String sourceStationName, String destinationStationName) {

	    boolean forSourceStation = true;
	    boolean forDestinationStation = true;
	    int totalNumberOfTrain = trains.size();
	    
	    for (int i = 0; i < totalNumberOfTrain ; i++) {
	        List<Integer> middleStationBetweenSourceStationAndDestinationStation = new ArrayList<Integer>();
	        List<Integer> totalStation = trains.get(i).getTotalStation();
	        int totalStationTrain = totalStation.size();
	        
	        for (int j = 0; j < totalStationTrain ; j++) {
	            if (forSourceStation) {
	                if (totalStation.get(j) == Integer.parseInt(searchTrain.getSourceStation())) {
	                    forSourceStation = false;
	                }
	                continue;
	            } else if (forDestinationStation) {
	                if (totalStation.get(j) == Integer.parseInt(searchTrain.getDestinationStation())) {
	                    forDestinationStation = false;
	                }
	                middleStationBetweenSourceStationAndDestinationStation.add(totalStation.get(j));
	                continue;
	            } else {
	                break;
	            }
	        }
	        seatAvalibility(trains.get(i), middleStationBetweenSourceStationAndDestinationStation, searchTrain);
	    }
	    return trains;
	}


	public void seatAvalibility(ITrain train, List < Integer > middleStationBetweenSourceAndDestination, ISearchTrain searchTrain) {

	    CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
	    ISeatAvailibilityDAO seatAvaillibilityDAO = calculationAbstractFactory.createNewSeatAvailibilityDAO();

	    int totalCoachesInTrain = train.getTotalCoaches();
	    int totalSeatsInOneCoach = 20;
	    int totalStationBetweenSourceAndDestination = middleStationBetweenSourceAndDestination.size();
	    int totalSeatsInTrain = totalCoachesInTrain * totalSeatsInOneCoach;

	    for (int i = 0; i < totalStationBetweenSourceAndDestination; i++) {
	        int bookedOne = seatAvaillibilityDAO.bookedTickets(searchTrain.getSourceStation().toString(), middleStationBetweenSourceAndDestination.get(i).toString(), train.getTrainId(), searchTrain.getDateofJourny());
	        totalSeatsInTrain = totalSeatsInTrain - bookedOne;
	    }
	    train.setAvailableSeat(totalSeatsInTrain);
	}

}
