package com.project.calculation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;

@Component
public class SeatAvailibilityDAO implements ISeatAvailibilityDAO{
	

	@Override
	public int bookedTickets(String sourceStation, String destinationStation, int trainID, Date date) {
		
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		
		int totalBookedTickets = 0;
	
		try {
			statment = connection.prepareCall("{call getBookedTicket( ? , ? , ? , ?)}");
			statment.setString(1, sourceStation);
			statment.setString(2, destinationStation);
			statment.setInt(3, trainID);
			statment.setString(4, convertDateUtilIntoSql(date));
			boolean hadResult =  statment.execute();
			if(hadResult) {
				resultSet = statment.getResultSet();
				while(resultSet.next()) {
					totalBookedTickets++;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return totalBookedTickets;
	}

	@Override
	public String convertDateUtilIntoSql(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date datefromArg = date;
		GregorianCalendar calendar = new GregorianCalendar();
		calendar.setTime(datefromArg);
		String finalDate = format.format(calendar.getTime());
		return finalDate;
	}
}
