package com.project.ticketprint;

public abstract class TicketPrintAbstractFactory {
	private static TicketPrintAbstractFactory instance = null;

	public static TicketPrintAbstractFactory instance() {
		if (null == instance) {
			instance = new TicketPrintConcreteFactory();
		}
		return instance;
	}

	public abstract ITicketPrint createNewTicketPrint();

	public abstract ITicketPrintDAO createNewTicketPrintDAO();

}
