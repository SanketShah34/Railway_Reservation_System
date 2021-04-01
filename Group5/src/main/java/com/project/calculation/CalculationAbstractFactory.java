package com.project.calculation;

public abstract class CalculationAbstractFactory {

	private static CalculationAbstractFactory instance = null;

	public abstract ISeatAvailibilityDAO createSeatAvailibilityDAO();

	public abstract ISeatAvailibilityDAO createNewSeatAvailibilityDAO();

	public abstract ITrainFilterAndCalculation createTrainFilterAndCalculateFair();

	public abstract ITrainFilterAndCalculation createNewTrainFilterAndCalculateFair();

	public abstract IAvailableSeats createAvaliableSeats();

	public abstract IAvailableSeats createNewAvaliableSeats();

	public static CalculationAbstractFactory instance() {
		if (null == instance) {
			instance = new CalculationConcreteFactory();
		}
		return instance;
	}

}
