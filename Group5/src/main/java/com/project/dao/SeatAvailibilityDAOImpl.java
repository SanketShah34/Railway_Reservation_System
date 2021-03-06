package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.project.service.DButilities;

@Component
public class SeatAvailibilityDAOImpl implements SeatAvailibilityDAO{
	
	@Autowired
	DButilities dbUtilities;

	@Override
	public int bookedTickets(String sourceStation, String destinationStation, int trainID, Date date) {
		int count = 0;
		System.out.println("--sourceStation--"+sourceStation+"--destinationStation--"+destinationStation+"--trainId--"+trainID+"--date--"+date);
		Connection conn = dbUtilities.estConnection();
		
		try {
			CallableStatement stmt = conn.prepareCall("{call getBookedTicket( ? , ? , ? , ?)}");
			stmt.setString(1, sourceStation);
			stmt.setString(2, destinationStation);
			stmt.setInt(3, trainID);
			
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date datefromArg = date; // mysql datetime format
			GregorianCalendar calendar = new GregorianCalendar();
			calendar.setTime(datefromArg);
			String finalDate = format.format(calendar.getTime());
			System.out.println(finalDate);
			
			stmt.setString(4, finalDate);

			boolean hadResult =  stmt.execute();
			if(hadResult) {
				ResultSet resultSet = stmt.getResultSet();
				while(resultSet.next()) {
					count++;
				}
			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}
		
		// TODO Auto-generated method stub
		return count;
	}

	

}
