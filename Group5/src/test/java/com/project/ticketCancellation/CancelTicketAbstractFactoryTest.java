package com.project.ticketCancellation;

public abstract class CancelTicketAbstractFactoryTest {
	private static  CancelTicketAbstractFactoryTest instance = null;
	public static CancelTicketAbstractFactoryTest instance() {
		return instance;
	/*
	 * if (null == instance) { instance = new CancelTicketConcreteFactoryTest(); }
	 * return instance; }
	 */
	}
	public abstract ISearchPassengerInfo createSearchPassengerInfo();

}
