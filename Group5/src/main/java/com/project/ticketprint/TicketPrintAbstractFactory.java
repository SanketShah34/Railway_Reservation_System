package com.project.ticketprint;

public abstract class TicketPrintAbstractFactory {

	private static TicketPrintAbstractFactory instance = null;
	
	public abstract ITicketPrint createNewTicketPrint();
	public abstract ITicketPrintDAO createNewTicketPrintDAO();
	
	public static TicketPrintAbstractFactory instance() {
		if (null == instance) {
			instance = new TicketPrintConcreteFactory();
		}
		return instance;
	}
}
