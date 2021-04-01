package com.project.ticketCancellation;


public class CancelTicketConcreteFactory extends CancelTicketAbstractFactory
{
	private ISearchPassengerInfo searchPassengerInfo;

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

}
