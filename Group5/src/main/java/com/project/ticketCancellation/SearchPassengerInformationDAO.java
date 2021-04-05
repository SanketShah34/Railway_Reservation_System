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
import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

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
	public final String TRAIN_TYPE = "trainType";
	public final String START_DATE = "startDate"; 
	public final String AMOUNT_INDIVIDUAL = "amount";
	public final String DEPARTURE_TIME = "departureTime";
	public final String TRAIN_ID = "trainId";
	public final String TICKET_BOOKED = "ticketBooked";
	public final String DESTINATION_STATION_ID = "destinationStationId";
	public final String SOURCE_STATION_ID = "sourceStationId";
	public final String TRAIN_CANCEL = "trainCancel";
	
	//public final double TWENTY_PERCENT = 0.2;
	//public final double FIFTY_PERCENT = 0.5;
	//public final String ADD_SECONDS = ":00";
	
	List<IPassengerInformation> passengerInfoList =   new ArrayList<IPassengerInformation>(); ;

	@Override
	public List<IPassengerInformation> SearchPassengerInfoByPNR(String pnrNumber)
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
					passengerInformation.setAmountPaid(resultSet.getDouble(AMOUNT_INDIVIDUAL));
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
	public IReservation GetAmountPaidOnTicket(List<Integer> ids) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		IReservation reservation = reservationAbstractFactory.createNewReservation();
		
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		int id = ids.get(0);
		String pnrNumber = GetPnrNumber(id);
		try {
			statment = connection.prepareCall("{call getAmountPaid(?)}");
			statment.setString(1, pnrNumber);
			boolean hadResult =  statment.execute();
			if(hadResult) {
				resultSet = statment.getResultSet();
				while(resultSet.next()) {					
					reservation.setAmountPaid(resultSet.getDouble(AMOUNT_PAID));
					reservation.setDestinationStationId(resultSet.getInt(DESTINATION_STATION_ID));
					reservation.setReservationId(resultSet.getInt(PNR_NUMBER));
					reservation.setSourceStationId(resultSet.getInt(SOURCE_STATION_ID));
					reservation.setStartDate(resultSet.getDate(START_DATE));
					reservation.setTrainCancelEvent(resultSet.getString(TRAIN_CANCEL));
					reservation.setTrainId(resultSet.getInt(TRAIN_ID));
					reservation.setTrainType(resultSet.getString(TRAIN_TYPE));
					reservation.setTicketBooked(resultSet.getInt(TICKET_BOOKED));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return reservation;
	}
	
	@Override
	public String GetPnrNumber(int id) {
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
	public ITrain GetTrainDetails(int trainId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		try {
			statment = connection.prepareCall("{call getTrainDetails(?)}");
			statment.setInt(1, trainId);
			boolean hadResult =  statment.execute();
			if(hadResult) {
				resultSet = statment.getResultSet();
				while(resultSet.next()) {
					train.setDepartureTime(resultSet.getString(DEPARTURE_TIME));
					train.setTrainType(resultSet.getString(TRAIN_TYPE));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return train;
	}

	/*@Override
	public double CalculateRefundAmount(IReservation reservation, List<Integer> ids) {
		int pnrNumber = reservation.getReservationId();
		double amountPaid = reservation.getAmountPaid();
		Date trainStartDate = reservation.getStartDate();
		double refundedAmount = 0.0;
		List<IPassengerInformation> passengerInformation = SearchPassengerInfoByPNR(String.valueOf(pnrNumber));
		List<IPassengerInformation> selectedPassengerInformation = new ArrayList<>();
		double amount = 0.0;
		for(IPassengerInformation information : passengerInformation) {
			for(Integer id : ids) {
				if(id == information.getPassengerInformationId()) {
					amount+= information.getAmountPaid();
					selectedPassengerInformation.add(information);
				}
			}
		}
		refundedAmount = amountPaid - amount;
		ITrain train = GetTrainDetails(reservation.getTrainId());
		refundedAmount = CalculateDiscount( amountPaid,  refundedAmount, trainStartDate, train.getDepartureTime());
		return refundedAmount;
	}

	private double CalculateDiscount(double amountPaid, double refundedAmount, Date trainStartDate, String departureTime) {
		LocalDateTime localDateTime = LocalDateTime.now();
		LocalDate localDate = localDateTime.toLocalDate();
		LocalTime localTime = localDateTime.toLocalTime();

		//https://www.baeldung.com/java-date-to-localdate-and-localdatetime
		LocalDate TrainDate = Instant.ofEpochMilli(trainStartDate.getTime()).atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localDateAfterAddingOneDay;
		Double amount;
		String dateStr = trainStartDate.toString();
		String trainDateTime = dateStr + " " + departureTime + ADD_SECONDS;	
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern( "yyyy-MM-dd HH:mm:ss" );
		LocalDateTime departureLocalDateTime = LocalDateTime.parse( trainDateTime , formatter);	
		LocalTime trainTime = departureLocalDateTime.toLocalTime();
		if(localDate.isEqual(TrainDate)) {
			amount = refundedAmount * TWENTY_PERCENT;
		}
		else {
			localDateAfterAddingOneDay = localDate.plusDays(1);
			if(localDateAfterAddingOneDay.isEqual(TrainDate)) {
				if(trainTime.isBefore(localTime)) {
					amount = refundedAmount * FIFTY_PERCENT;
				}
				else {
					amount = refundedAmount * TWENTY_PERCENT;
				}
			}
			else {
				amount = refundedAmount * FIFTY_PERCENT;
			}
		}
		return amount;
	}*/

	@Override
	public void DeleteTickets(List<Integer> ids, IReservation reservation, double refundedAmount) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		CallableStatement statment1 = null;
		String pnrNumber = null;
		int idForPnr = ids.get(0);
		pnrNumber = GetPnrNumber(idForPnr);
		Double amountPaid = reservation.getAmountPaid();
		int totalTicketBooked = reservation.getTicketBooked();
		int remainingTickets = totalTicketBooked - ids.size();
		int deletedTicket = 0;
		if(remainingTickets == 0) {
			deletedTicket = 1;
		}
		Double finalAmount = amountPaid - refundedAmount;
		try {
			for(Integer id : ids) {
				statment = connection.prepareCall("{call deleteTicketRecords(?)}");
				statment.setInt(1, id);
				statment.execute();
			}
			statment1 = connection.prepareCall("{call updateReservationRecords(?, ?, ?)}");
			statment1.setString(1, pnrNumber); 
			statment1.setDouble(2, finalAmount);
			statment1.setDouble(3, deletedTicket);
			
			statment1.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeStatement(statment1);
			databaseUtilities.closeConnection(connection);
		}
	}
}
