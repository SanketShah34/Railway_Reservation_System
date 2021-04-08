package com.project.seatallocation;

public abstract class SeatAllocationAbstractFactoryTest {

	private static SeatAllocationAbstractFactoryTest instance = null;

	public static SeatAllocationAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new SeatAllocationConcreteFactoryTest();
		}
		return instance;
	}
	
	public abstract SeatAllocationDAOMock createSeatAllocationDAOMock();

}