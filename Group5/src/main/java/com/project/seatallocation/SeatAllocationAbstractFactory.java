package com.project.seatallocation;

public abstract class SeatAllocationAbstractFactory {

	private static SeatAllocationAbstractFactory instance = null;

	public static SeatAllocationAbstractFactory instance() {
		if (null == instance) {
			instance = new SeatAllocationConcreteFactory();
		}
		return instance;
	}

	public abstract ISeatAllocationDAO createNewSeatAllocationDAO();

}