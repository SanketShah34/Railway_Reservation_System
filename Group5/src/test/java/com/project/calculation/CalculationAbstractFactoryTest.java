package com.project.calculation;

public abstract class CalculationAbstractFactoryTest {
	
	private static CalculationAbstractFactoryTest instance = null;

	public static CalculationAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new CalculationConcreteFactoryTest();
		}
		return instance;
	}
	
	public abstract SeatAvailibilityDAOMock createSeatAvailibilityDAOMock();
	
	public abstract BookedTicketsMock createBookedTicketsMock();
}
