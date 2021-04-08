package com.project.seatallocation;

public class SeatAllocationConcreteFactoryTest extends SeatAllocationAbstractFactoryTest{

	@Override
	public SeatAllocationDAOMock createSeatAllocationDAOMock() {
		return new SeatAllocationDAOMock();
	}
}
