package com.project.calculation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;

@Component
public class SeatAvailibilityDAO implements ISeatAvailibilityDAO {
	public final String maxSeatNoColumnName = "maxSeatNo";
	public final String reservationIdColumnName = "reservationId";
	public final String trainIdColumnName = "trainId";
	public final String dateColumnName = "reservationDate";
	public final String sourceStationIdColumnName = "sourceStationId";
	public final String destinationStationIdColumnName = "destinationStationId";
	public final String amountPaidColumnName = "amountPaid";
	public final String ticketBookedColumnName = "ticketBooked";
	public final String userIdColumnName = "userId";
	public final String reservationIdStringColumnName = "reservationId";

	@Override
	public List<IBookedTickets> getListOfTicketsFromSeatNo(ITrain train, Date date, int seatNo) {
		List<IBookedTickets> bookedTickets = new ArrayList<IBookedTickets>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		try {
			statment = connection.prepareCall("{call getBookedTicketsOFThatDay( ? , ? , ?)}");
			statment.setDate(1, date);
			statment.setInt(2, train.getTrainId());
			statment.setInt(3, seatNo);
			boolean hadResult = statment.execute();
			if (hadResult) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					IBookedTickets ticket = calculationAbstractFactory.createNewBookedTickets();
					ticket.setReservationId(resultSet.getInt(reservationIdColumnName));
					ticket.setTrainId(resultSet.getInt(trainIdColumnName));
					ticket.setReservationDate(resultSet.getDate(dateColumnName));
					ticket.setSourceStationId(resultSet.getInt(sourceStationIdColumnName));
					ticket.setDestinationId(resultSet.getInt(destinationStationIdColumnName));
					ticket.setAmountPaid(resultSet.getDouble(amountPaidColumnName));
					ticket.setTicketBooked(resultSet.getInt(ticketBookedColumnName));
					ticket.setUserId(resultSet.getInt(userIdColumnName));
					bookedTickets.add(ticket);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return bookedTickets;
	}

	@Override
	public List<Integer> getReservationId(ITrain train, Date date) {
		int reservationId = 0;
		List<Integer> reservationIds = new ArrayList<Integer>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		try {
			statment = connection.prepareCall("{call getReservationId( ? , ? )}");
			statment.setInt(1, train.getTrainId());
			statment.setDate(2, date);
			boolean hadResult = statment.execute();
			if (hadResult) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					reservationId = resultSet.getInt(reservationIdStringColumnName);
					reservationIds.add(reservationId);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return reservationIds;
	}

	@Override
	public int maximumSeatNumberOfReservationId(int reservationId) {
		int maximumNumber = 0;
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statment = null;
		ResultSet resultSet = null;
		try {
			statment = connection.prepareCall("{call getMaximumSeatNo( ?  )}");
			statment.setInt(1, reservationId);
			boolean hadResult = statment.execute();
			if (hadResult) {
				resultSet = statment.getResultSet();
				while (resultSet.next()) {
					maximumNumber = resultSet.getInt(maxSeatNoColumnName);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statment);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return maximumNumber;
	}

}
