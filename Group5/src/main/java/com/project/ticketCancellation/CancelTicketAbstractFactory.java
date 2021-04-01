package com.project.ticketCancellation;

public abstract class CancelTicketAbstractFactory
{
	private static  CancelTicketAbstractFactory instance = null;
	
	public abstract ISearchPassengerInfo createSearchPassengerInfo();
	public abstract ISearchPassengerInfo createNewSearchPassengerInfo();
	
	public static CancelTicketAbstractFactory instance() {
		if (null == instance) {
			instance = new CancelTicketConcreteFactory();
		}
		return instance;
	}
}
