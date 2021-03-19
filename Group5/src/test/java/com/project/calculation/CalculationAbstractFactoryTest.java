package com.project.calculation;

public abstract class CalculationAbstractFactoryTest {
private static CalculationAbstractFactoryTest instance = null;
	
	public static CalculationAbstractFactoryTest instance() {
		if (instance == null) {
			instance = new CalculationConcreteFactoryTest();
		}
		return instance;
	}

	public abstract SeatAvailibilityDAOMock createSeatAvailibilityDAOMock();
//	public abstract FindFareMock createFindFair();
//	public abstract AvailableSeatsMock createAvaliableSeats();
	

	

}
