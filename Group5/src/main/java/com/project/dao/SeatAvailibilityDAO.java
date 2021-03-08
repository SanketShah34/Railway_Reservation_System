package com.project.dao;

import java.util.Date;

public interface SeatAvailibilityDAO {

	public int bookedTickets(String sourceStation , String destinationStation , int trainID , Date date);
}
