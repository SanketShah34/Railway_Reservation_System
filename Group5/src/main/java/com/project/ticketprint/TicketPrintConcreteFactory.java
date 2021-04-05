package com.project.ticketprint;

public class TicketPrintConcreteFactory extends TicketPrintAbstractFactory{

	private ITicketPrint ticketPrint;
	private ITicketPrintDAO ticketPrintDAO;
	
	@Override
	public ITicketPrintDAO createNewTicketPrintDAO() {
		if(null == ticketPrintDAO) {
			ticketPrintDAO = new TicketPrintDAO();
		}
		return ticketPrintDAO;
	}
	
	@Override
	public ITicketPrint createNewTicketPrint() {
		if(null == ticketPrint) {
			ticketPrint = new TicketPrint();
		}
		return ticketPrint;
	}
}