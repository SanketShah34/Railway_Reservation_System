package com.project.cancelTrain;

import com.project.setup.ICancelTrain;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;

public class TrainCancellationDAO implements ITrainCancellationDAO {

	public final String trainIdColumnName = "trainId";
	public final String reservationIdColumnName = "reservationId";
	public final String reservationAmountPaid = "totalReservationAmount";
	public final String sourceStationIdColumnName = "sourceStationId";
	public final String destinationStationIdColumnName = "destinationStationId";
	public final String ticketBookedColumnName = "ticketBooked";
	public final String trainTypeColumnName = "trainType";
	public final String startDateColumnName = "startDate";
	public final String trainCancelColumnName = "trainCancel";
	public final String amountPaidColumnName = "totalReservationAmount";
	public final String passengerInformationIdColumnName = "passengerInformationId";
	public final String firstNameColumnName = "firstName";
	public final String lastNameColumnName = "lastName";
	public final String genderColumnName = "gender";
	public final String ageColumnName = "age";
	public final String berthPreferenceColumnName = "berthPreference";
	public final String seatNumberColumnName = "seatNumber";
	public final String coachNumberColumnName = "coachNumber";
	
	@Override
	public List<IReservation> fetchAllReservations(ICancelTrain cancelTrain) {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		List<IReservation> reservationList = new ArrayList<IReservation>(0);
		
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		int trainId = 0;
		try {
			statement = connection.prepareCall("{call getTrainId(?)}");
			statement.setInt(1, cancelTrain.getTrainCode());
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				trainId = resultSet.getInt(trainIdColumnName);
			}
			if (trainId > 0) {
				statement = connection.prepareCall("{call getAllReservations(?, ?)}");
				statement.setInt(1, trainId);
				statement.setString(2, cancelTrain.getCancellationDate().toString());
				resultSet = statement.executeQuery();
				while(resultSet.next()) {
					int reservationId = resultSet.getInt(reservationIdColumnName);
					int indexExists = -1;
					for (int index = 0; index < reservationList.size(); index++) {
						if (reservationId == reservationList.get(index).getReservationId()) {
							indexExists = index;
							break;
						}
					}
					if (indexExists >= 0) {
						IReservation reservation = reservationList.get(indexExists);
						
						IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
						passengerInformation.setAge(resultSet.getInt(ageColumnName));
						passengerInformation.setBerthPreference(resultSet.getString(berthPreferenceColumnName));
						passengerInformation.setFirstName(resultSet.getString(firstNameColumnName));
						passengerInformation.setGender(resultSet.getString(genderColumnName));
						passengerInformation.setLastName(resultSet.getString(lastNameColumnName));
						passengerInformation.setPassengerInformationId(resultSet.getInt(passengerInformationIdColumnName));
						passengerInformation.setReservationId(reservationId);
						reservation.addInPassengerInformationList(reservation.getPassengerInformation(), passengerInformation);
						reservationList.set(indexExists, reservation);
						
					} else {
						IReservation reservation = reservationAbstractFactory.createNewReservation();
						List<IPassengerInformation> passengerInformationList = new ArrayList<IPassengerInformation>(0);
						IPassengerInformation passengerInformation = reservationAbstractFactory.createNewPassengerInformation();
						
						reservation.setAmountPaid(resultSet.getDouble(reservationAmountPaid));
						reservation.setDestinationStationId(resultSet.getInt(destinationStationIdColumnName));
						reservation.setReservationId(resultSet.getInt(reservationIdColumnName));
						reservation.setSourceStationId(resultSet.getInt(sourceStationIdColumnName));
						reservation.setStartDate(resultSet.getDate(startDateColumnName));
						reservation.setTrainCancelEvent(resultSet.getString(trainCancelColumnName));
						reservation.setTrainId(resultSet.getInt(trainIdColumnName));
						reservation.setTrainType(resultSet.getString(trainTypeColumnName));
						reservation.setReservationId(reservationId);
						reservation.setTicketBooked(resultSet.getInt(ticketBookedColumnName));
						
						passengerInformation.setAge(resultSet.getInt(ageColumnName));
						passengerInformation.setBerthPreference(resultSet.getString(berthPreferenceColumnName));
						passengerInformation.setFirstName(resultSet.getString(firstNameColumnName));
						passengerInformation.setGender(resultSet.getString(genderColumnName));
						passengerInformation.setLastName(resultSet.getString(lastNameColumnName));
						passengerInformation.setPassengerInformationId(resultSet.getInt(passengerInformationIdColumnName));
						passengerInformation.setReservationId(reservationId);
						
						passengerInformationList.add(passengerInformation);
						
						reservation.setPassengerInformation(passengerInformationList);
						reservationList.add(reservation);
					}
				}
			}
			
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return reservationList;
	}
}
