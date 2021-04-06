package com.project.ticketCancellation;

public class CancelTicketConcreteFactoryTest extends CancelTicketAbstractFactoryTest {
	
	private SearchPassengerInformationDAOTest searchPassengerInfoTest;
	private CalculateAmountTest calculateAmountTest;

	@Override
	public SearchPassengerInformationDAOTest createSearchPassengerInfoTest() {
		if (searchPassengerInfoTest == null) {
			searchPassengerInfoTest = new SearchPassengerInformationDAOTest();
    	}
		return new SearchPassengerInformationDAOTest();
	}

	@Override
	public SearchPassengerInformationDAOTest createNewSearchPassengerInfoTest() {
		return new SearchPassengerInformationDAOTest();
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
	
	@Override
	public SearchPassengerInformationDAOMock createSearchPassengerInformationDAOMock() {
		return new SearchPassengerInformationDAOMock();
	}

}
