package com.project.calculation;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.project.reservation.PassengerMock;

class CalculationConcreteFactoryTest extends CalculationAbstractFactoryTest{

	@Override
	public SeatAvailibilityDAOMock createSeatAvailibilityDAOMock() {
		return new SeatAvailibilityDAOMock();
	}


	

}
