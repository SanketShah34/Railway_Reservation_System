package com.project.ticketprint;

public class TicketPrintConcreteFactoryTest extends TicketPrintAbstractFactoryTest {

	@Override
	public TicketPrintMock createTicketPrintMock() {
		return new TicketPrintMock();
	}

}