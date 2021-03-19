package com.project.calculation;

public class CalculationConcreteFactory extends CalculationAbstractFactory{
	
	private IFindFare findFair;
	private IAvailableSeats avialableSeats;
	private ISeatAvailibilityDAO seatAvailibilityDAO;
	
	@Override
	public ISeatAvailibilityDAO createSeatAvailibilityDAO() {
		if (seatAvailibilityDAO == null) {
			seatAvailibilityDAO = new SeatAvailibilityDAO();
    	}
    	return seatAvailibilityDAO;	
	}

	@Override
	public ISeatAvailibilityDAO createNewSeatAvailibilityDAO() {
		return new SeatAvailibilityDAO();
	}
	
	@Override
	public IFindFare createFindFair() {
		if (findFair == null) {
			findFair = new FindFare();
    	}
    	return findFair;	
	}
	
	@Override
	public IFindFare createNewFindFair() {
		return new FindFare();
	}

	@Override
	public IAvailableSeats createAvaliableSeats() {
		if (avialableSeats == null) {
			avialableSeats = new AvailableSeats();
    	}
    	return avialableSeats;
	}

	@Override
	public IAvailableSeats createNewAvaliableSeats() {
		return new AvailableSeats();
	}

}
