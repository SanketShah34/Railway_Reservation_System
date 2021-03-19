package com.project.calculation;

public abstract class CalculationAbstractFactoryTest {
	public static CalculationAbstractFactoryTest instance = null;
	
	public static CalculationAbstractFactoryTest instance() {
		if (instance == null) {
			instance = new CalculationConcreteFactoryTest();
		}
		return instance;
	}
	
	public abstract SeatAvailibilityDAOMock createSeatAvailibilityDAOMock();
}
