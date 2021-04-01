package com.project.ticketCancellation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.ReservationAbstractFactory;

public class SearchPassengerInfo implements ISearchPassengerInfo
{
	
	public final String FIRSTNAME = "firstName";
	public final String LASTNAME = "lastName";
	public final String GENDER = "gender";
	public final String AGE = "age";
	public final String BERTHPREFERENCE = "berthPreference";
	public final String PASSENGER_INFORMATION_ID = "passengerInformationId";
	public final String PNR_NUMBER = "reservationId";
	public final String AMOUNT_PAID = "amountPaid";
	List<IPassengerInformation> passengerInfoList =   new ArrayList<IPassengerInformation>(); ;

	@Override
	public List<IPassengerInformation> SearchPassengerInfoByPNR(String userName, String pnrNumber)
	{
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		passengerInfoList.removeAll(passengerInfoList);
		
		try {
			statment = connection.prepareCall("{call getTicketInformation(?)}");
			statment.setString(1, pnrNumber);
			boolean hadResult =  statment.execute();
			if(hadResult) {
				resultSet = statment.getResultSet();
				while(resultSet.next()) {
					IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation(); 
					passengerInformation.setPassengerInformationId(resultSet.getInt(PASSENGER_INFORMATION_ID));
					passengerInformation.setFirstName(resultSet.getString(FIRSTNAME));
					passengerInformation.setLastName(resultSet.getString(LASTNAME));
					passengerInformation.setGender(resultSet.getString(GENDER));
					passengerInformation.setAge(resultSet.getInt(AGE));
					passengerInformation.setBerthPreference(resultSet.getString(BERTHPREFERENCE));
					passengerInfoList.add(passengerInformation);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		
		return passengerInfoList;
	}

	@Override
	public double getAmountPaidOnTicket(List<Integer> ids) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		double amountPaid = 0;
		int id = ids.get(0);
		String pnrNumber = getPnrNumber(id);
		try {
			statment = connection.prepareCall("{call getAmountPaid(?)}");
			statment.setString(1, pnrNumber);
			boolean hadResult =  statment.execute();
			if(hadResult) {
				resultSet = statment.getResultSet();
				while(resultSet.next()) {
					amountPaid = resultSet.getDouble(AMOUNT_PAID);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return amountPaid;
	}
	
	@Override
	public String getPnrNumber(int id) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		String pnrNumber = null;
		try {
			statment = connection.prepareCall("{call getPNRNumber(?)}");
			statment.setInt(1, id);
			boolean hadResult =  statment.execute();
			if(hadResult) {
				resultSet = statment.getResultSet();
				while(resultSet.next()) {
					pnrNumber = resultSet.getString(PNR_NUMBER);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return pnrNumber;
	}

	@Override
	public double calculateRefundAmount(double amountPaid) {
		
		return 0;
	}
}
