package com.project.ticketCancellation;


public class CancelTicketConcreteFactory extends CancelTicketAbstractFactory {
	private ISearchPassengerInfo searchPassengerInfo;
	private ICalculateAmounts calculateAmounts;

	@Override
	public ISearchPassengerInfo createSearchPassengerInfo() {
		if (searchPassengerInfo == null) {
			searchPassengerInfo = new SearchPassengerInfo();
    	}
    	return searchPassengerInfo;	
	}

	@Override
	public ISearchPassengerInfo createNewSearchPassengerInfo() {
		return new SearchPassengerInfo();
	}

	@Override
	public ICalculateAmounts createCalculateAmounts() {
		if (calculateAmounts == null) {
			calculateAmounts = new CalculateAmounts();
    	}
    	return calculateAmounts;	
	}

	@Override
	public ICalculateAmounts createNewCalculateAmounts() {
		return new CalculateAmounts();
	}

}
