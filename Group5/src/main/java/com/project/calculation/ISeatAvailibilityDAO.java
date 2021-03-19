package com.project.calculation;

import java.util.Date;

public interface ISeatAvailibilityDAO {

	public int bookedTickets(String sourceStation , String destinationStation , int trainID , Date date);
	
	public String convertDateUtilIntoSql(Date date);
}
