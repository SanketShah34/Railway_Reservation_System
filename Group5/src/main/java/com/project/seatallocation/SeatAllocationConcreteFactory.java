package com.project.seatallocation;

public class SeatAllocationConcreteFactory extends SeatAllocationAbstractFactory {

	private ISeatAllocationDAO seatAllocationDAO;
	
	@Override
	public ISeatAllocationDAO createNewSeatAllocationDAO() {
		if(null == seatAllocationDAO) {
			seatAllocationDAO = new SeatAllocationDAO();
		}
		return seatAllocationDAO;
	}
}
