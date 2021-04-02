package com.project.ticketCancellation;

public class CancelTicketConcreteFactoryTest extends CancelTicketAbstractFactoryTest {
	
	private SearchPassengerInfoTest searchPassengerInfoTest;

	@Override
	public SearchPassengerInfoTest createSearchPassengerInfoTest() {
		if (searchPassengerInfoTest == null) {
			searchPassengerInfoTest = new SearchPassengerInfoTest();
    	}
		return new SearchPassengerInfoTest();
	}

	@Override
	public SearchPassengerInfoTest createNewSearchPassengerInfoTest() {
		return new SearchPassengerInfoTest();
	}

}
