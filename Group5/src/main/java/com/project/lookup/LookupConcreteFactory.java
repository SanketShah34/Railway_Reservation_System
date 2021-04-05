package com.project.lookup;

public class LookupConcreteFactory extends LookupAbstractFactory {

	private ISearchTrainDAO searchTrainDAO;
	private ISearchTrain searchtrain;
	private ITrainFilterAndFairCalculation trainFilterAndCalculation;
	private IAvailableSeats avialableSeats;
	private ISeatAvailibilityDAO seatAvailibilityDAO;
	private IBookedTickets bookedTickets;
	private IDayCalculation dayCalculation;

	public ISearchTrainDAO createSearchTrainDAO() {
		if (searchTrainDAO == null) {
			searchTrainDAO = new SearchTrainDAO();
		}
		return searchTrainDAO;
	}

	public ISearchTrainDAO createNewSearchTrainDAO() {
		return new SearchTrainDAO();
	}

	public ISearchTrain createSearchTrain() {
		if (searchtrain == null) {
			searchtrain = new SearchTrain();
		}
		return searchtrain;
	}

	public ISearchTrain createNewSearchTrain() {
		return new SearchTrain();
	}
	
	public ISeatAvailibilityDAO createSeatAvailibilityDAO() {
		if (seatAvailibilityDAO == null) {
			seatAvailibilityDAO = new SeatAvailibilityDAO();
		}
		return seatAvailibilityDAO;
	}

	public ISeatAvailibilityDAO createNewSeatAvailibilityDAO() {
		return new SeatAvailibilityDAO();
	}

	public ITrainFilterAndFairCalculation createTrainFilterAndCalculateFair() {
		if (trainFilterAndCalculation == null) {
			trainFilterAndCalculation = new TrainFilterAndFairCalculation();
		}
		return trainFilterAndCalculation;
	}

	public ITrainFilterAndFairCalculation createNewTrainFilterAndCalculateFair() {
		return new TrainFilterAndFairCalculation();
	}

	public IAvailableSeats createAvaliableSeats() {
		if (avialableSeats == null) {
			avialableSeats = new AvailableSeats();
		}
		return avialableSeats;
	}

	public IAvailableSeats createNewAvaliableSeats() {
		return new AvailableSeats();
	}

	public IBookedTickets createBookedTickets() {
		if (bookedTickets == null) {
			bookedTickets = new BookedTickets();
		}
		return bookedTickets;
	}

	public IBookedTickets createNewBookedTickets() {
		return new BookedTickets();
	}

	@Override
	public IDayCalculation createDayCalculation() {
		if (dayCalculation == null) {
			dayCalculation = new DaysCalculation();
		}
		return dayCalculation;
	}

	@Override
	public IDayCalculation createNewDayCalculation() {
		return new DaysCalculation();
	}

}
