package com.project.calculation;

class CalculationConcreteFactoryTest extends CalculationAbstractFactoryTest{

	
	@Override
	public SeatAvailibilityDAOMock createSeatAvailibilityDAOMock() {
		return new SeatAvailibilityDAOMock();
	}


	

}
