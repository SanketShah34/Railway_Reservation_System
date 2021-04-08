package com.project.ticketemail;

public class TicketEmailConcreteFactoryTest extends TicketEmailAbstractFactoryTest{

	@Override
	public TicketEmailMock createTicketEmailMock() {
		return new TicketEmailMock();
	}
}