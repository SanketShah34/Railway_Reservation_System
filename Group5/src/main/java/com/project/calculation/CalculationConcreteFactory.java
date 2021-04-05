package com.project.calculation;

public class CalculationConcreteFactory extends CalculationAbstractFactory {

	private ITrainFilterAndCalculation trainFilterAndCalculation;
	private IAvailableSeats avialableSeats;
	private ISeatAvailibilityDAO seatAvailibilityDAO;

	public ISeatAvailibilityDAO createSeatAvailibilityDAO() {
		if (seatAvailibilityDAO == null) {
			seatAvailibilityDAO = new SeatAvailibilityDAO();
		}
		return seatAvailibilityDAO;
	}

	public ISeatAvailibilityDAO createNewSeatAvailibilityDAO() {
		return new SeatAvailibilityDAO();
	}

	public ITrainFilterAndCalculation createTrainFilterAndCalculateFair() {
		if (trainFilterAndCalculation == null) {
			trainFilterAndCalculation = new TrainFilterAndFairCalculation();
		}
		return trainFilterAndCalculation;
	}

	public ITrainFilterAndCalculation createNewTrainFilterAndCalculateFair() {
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

}
