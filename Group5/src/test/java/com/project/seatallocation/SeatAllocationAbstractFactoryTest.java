package com.project.seatallocation;

public class SeatAllocationAbstractFactoryTest {

	private static SeatAllocationAbstractFactoryTest instance = null;
	
	public static SeatAllocationAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new SeatAllocationAbstractFactoryTest();
		}
		return instance;
	}
	
}