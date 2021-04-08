package com.project.lookup;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.project.setup.ITrain;

@Component
public class AvailableSeats implements IAvailableSeats {
	private final int SEATS_IN_ONE_COACH = 32;
	
	@Override
	public List<ITrain> findAvailableSeats(List<ITrain> trains, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvalibilityDAO) {
		int totalNumberOfTrain = 0;
		
		totalNumberOfTrain = trains.size();
		for (int i = 0; i < totalNumberOfTrain; i++) {
			findAvailableSeatCountInSingleTrain(trains.get(i), searchTrain, seatAvalibilityDAO);
		}
		return trains;
	}

	@Override
	public List<Integer> listOfMiddleStation(ITrain train, ISearchTrain searchTrain) {
		int sourceStationIndex = 0;
		int destinationStationIndex = 0;
		List<Integer> totalStation = train.getTotalStation();
		List<Integer> middleStationList = new ArrayList<Integer>();
		
		sourceStationIndex = Integer.parseInt(searchTrain.getSourceStation());
		destinationStationIndex = Integer.parseInt(searchTrain.getDestinationStation());
		for (int j = sourceStationIndex - 1; j < destinationStationIndex; j++) {
			middleStationList.add(totalStation.get(j));
		}
		return middleStationList;
	}

	@Override
	public void findAvailableSeatCountInSingleTrain(ITrain train, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvaillibilityDAO) {
		int seatAvailableFromBookedSeat = 0;
		int firstIndexOfMiddleStation = 0;
		int lastIndexOfMiddleStation = 0;
		int sourceStationIndexOfBookedTicket = 0;
		int destinationStationIndexOfBookedTicket = 0;
		int seatThatIsAvailableForBooking = 0;
		int firstIndexOfStationFromTripCode = 0;
		int lastIndexOfStationFromTripCode = 0;
		int totalSeatInOneTrain = 0;
		boolean isSeatAvailable = true;
		int maximumSeatNoFromDatabase = 0;
		
		maximumSeatNoFromDatabase = findMaximumSeatInSingleTrain(train, train.getStartDate(), seatAvaillibilityDAO);
		if (maximumSeatNoFromDatabase == 0) {

		} else {
			for (int i = 1; i <= maximumSeatNoFromDatabase; i++) {
				List<IBookedTickets> bookedTickets = seatAvaillibilityDAO.getListOfTicketsFromSeatNo(train,
						train.getStartDate(), i);
				
				isSeatAvailable = true;
				for (int j = 0; j < bookedTickets.size(); j++) {
					List<Integer> allStation = train.getTotalStation();
					List<Integer> stationOfTripCode = new ArrayList<Integer>();
					
					sourceStationIndexOfBookedTicket = bookedTickets.get(j).getSourceStationId();
					destinationStationIndexOfBookedTicket = bookedTickets.get(j).getDestinationId();
					for (int r = sourceStationIndexOfBookedTicket; r <= destinationStationIndexOfBookedTicket; r++) {
						stationOfTripCode.add(allStation.get(r - 1));
					}
					List<Integer> afterRemovingSourceStationFromTripCode = new ArrayList<Integer>();
					List<Integer> afterRemovingDesinationStationFromTripCode = new ArrayList<Integer>();
					
					for (int k = 0; k < stationOfTripCode.size(); k++) {
						afterRemovingSourceStationFromTripCode.add(stationOfTripCode.get(k));
						afterRemovingDesinationStationFromTripCode.add(stationOfTripCode.get(k));
					}
					firstIndexOfStationFromTripCode = 0;
					afterRemovingSourceStationFromTripCode.remove(firstIndexOfStationFromTripCode);
					lastIndexOfStationFromTripCode = stationOfTripCode.size() - 1;
					afterRemovingDesinationStationFromTripCode.remove(lastIndexOfStationFromTripCode);
					List<Integer> middleStationAfterRemovingSourceStation = new ArrayList<Integer>();
					
					middleStationAfterRemovingSourceStation = listOfMiddleStation(train, searchTrain);
					middleStationAfterRemovingSourceStation.remove(firstIndexOfMiddleStation);
					for (int k = 0; k < middleStationAfterRemovingSourceStation.size(); k++) {
						for (int l = 0; l < afterRemovingSourceStationFromTripCode.size(); l++) {
							if (middleStationAfterRemovingSourceStation.get(k) == afterRemovingSourceStationFromTripCode
									.get(l)) {
								isSeatAvailable = false;
								break;
							}
						}
					}
					List<Integer> middleStationAfterRemovingDestinationStation = new ArrayList<Integer>();
					
					middleStationAfterRemovingDestinationStation = listOfMiddleStation(train, searchTrain);
					lastIndexOfMiddleStation = middleStationAfterRemovingDestinationStation.size() - 1;
					middleStationAfterRemovingDestinationStation.remove(lastIndexOfMiddleStation);
					for (int m = 0; m < middleStationAfterRemovingDestinationStation.size(); m++) {
						for (int n = 0; n < afterRemovingDesinationStationFromTripCode.size(); n++) {
							if (middleStationAfterRemovingDestinationStation
									.get(m) == afterRemovingDesinationStationFromTripCode.get(n)) {
								isSeatAvailable = false;
								break;
							}
						}
					}
				}
				if (isSeatAvailable) {
					seatAvailableFromBookedSeat++;
				}
			}
		}
		totalSeatInOneTrain = train.getTotalCoaches() * SEATS_IN_ONE_COACH;
		seatThatIsAvailableForBooking = totalSeatInOneTrain - maximumSeatNoFromDatabase + seatAvailableFromBookedSeat;
		train.setAvailableSeat(seatThatIsAvailableForBooking);
	}

	@Override
	public int findMaximumSeatInSingleTrain(ITrain train, Date date, ISeatAvailibilityDAO seatAvaillibilityDAO) {
		int maximumSeatNumber = 0;
		int maximumSeatNumberOfThatReservationId = 0;
		List<Integer> reservationIds = seatAvaillibilityDAO.getReservationId(train, date);
		
		for (int i = 0; i < reservationIds.size(); i++) {
			maximumSeatNumberOfThatReservationId = seatAvaillibilityDAO
					.maximumSeatNumberOfReservationId(reservationIds.get(i));
			if (maximumSeatNumberOfThatReservationId > maximumSeatNumber) {
				maximumSeatNumber = maximumSeatNumberOfThatReservationId;
			}
		}
		return maximumSeatNumber;
	}
	
}
