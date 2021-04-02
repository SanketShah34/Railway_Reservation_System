package com.project.ticketCancellation;

public abstract class CancelTicketAbstractFactoryTest {
	private static  CancelTicketAbstractFactoryTest instance = null;
	public static CancelTicketAbstractFactoryTest instance() {
		if (instance == null) {
			instance = new CancelTicketConcreteFactoryTest();
		}
		return instance;
	}
	public abstract SearchPassengerInfoTest createSearchPassengerInfoTest();
	
	public abstract SearchPassengerInfoTest createNewSearchPassengerInfoTest();
}

