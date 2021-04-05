package com.project.ticketCancellation;


public class CancelTicketConcreteFactory extends CancelTicketAbstractFactory {
	private ISearchPassengerInformationDAO searchPassengerInfo;
	private ICalculateAmounts calculateAmounts;

	@Override
	public ISearchPassengerInformationDAO createSearchPassengerInfo() {
		if (searchPassengerInfo == null) {
			searchPassengerInfo = new SearchPassengerInformationDAO();
    	}
    	return searchPassengerInfo;	
	}

	@Override
	public ISearchPassengerInformationDAO createNewSearchPassengerInfo() {
		return new SearchPassengerInformationDAO();
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
