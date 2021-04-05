package com.project.ticketCancellation;

public class CancelTicketConcreteFactoryTest extends CancelTicketAbstractFactoryTest {
	
	private SearchPassengerInfoTest searchPassengerInfoTest;
	private CalculateAmountTest calculateAmountTest;

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

	@Override
	public CalculateAmountTest createCalculateAmountTest() {
		if (calculateAmountTest == null) {
			calculateAmountTest = new CalculateAmountTest();
    	}
		return new CalculateAmountTest();
	}

	@Override
	public CalculateAmountTest createNewCalculateAmountTest() {
		return new CalculateAmountTest();
	}

}
