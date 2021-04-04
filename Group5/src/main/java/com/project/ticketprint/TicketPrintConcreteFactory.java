package com.project.ticketprint;

public class TicketPrintConcreteFactory extends TicketPrintAbstractFactory{

	private ITicketPrintDAO ticketPrintDAO;
	
	@Override
	public ITicketPrintDAO createNewTicketPrintDAO() {
		if(null == ticketPrintDAO) {
			ticketPrintDAO = new TicketPrintDAO();
		}
		return ticketPrintDAO;
	}
}
