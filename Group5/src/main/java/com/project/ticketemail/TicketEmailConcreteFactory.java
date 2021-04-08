package com.project.ticketemail;

public class TicketEmailConcreteFactory extends TicketEmailAbstractFactory{

	private ITicketEmail ticketPrint;
	private ITicketEmailDAO ticketPrintDAO;
	
	@Override
	public ITicketEmailDAO createNewTicketEmailDAO() {
		if(null == ticketPrintDAO) {
			ticketPrintDAO = new TicketEmailDAO();
		}
		return ticketPrintDAO;
	}
	
	@Override
	public ITicketEmail createNewTicketEmail() {
		if(null == ticketPrint) {
			ticketPrint = new TicketEmail();
		}
		return ticketPrint;
	}
}