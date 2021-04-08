package com.project.findMyTrain;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

public class FindMyTrainDAO implements IFindMyTrainDAO {
	
	public final String daysColumnName = "days";
	public final String departureTimeColumnName = "departureTime";
	public final String startStationColumnName = "startStation";
	public final String middleStationsColumnName = "middleStations";
	public final String endStationColumnName = "endStation";
	public final String distanceColumnName = "distance";
	public final String stationIdColumnName = "stationId";
	public final String stationNameColumnName = "stationName";
	
	public ITrain getLiveTrainStatus(int trainCode, Date startDate) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		ITrain train = setupAbstractFactory.createNewTrain();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareCall("{call getTrain(?)}");
			statement.setInt(1, trainCode);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				train.setDays(resultSet.getString(daysColumnName));
				train.setDepartureTime(resultSet.getString(departureTimeColumnName));
				train.setStartStation(resultSet.getString(startStationColumnName));
				train.setMiddleStations(resultSet.getString(middleStationsColumnName));
				train.setEndStation(resultSet.getString(endStationColumnName));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return train;
	}

	public double getRouteInformation(Integer startStation, Integer endStation) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		double distance = 0;
		try {
			statement = connection.prepareCall("{call getRoutebyStation(?, ?)}");
			statement.setInt(1, startStation);
			statement.setInt(2, endStation);
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				distance = resultSet.getDouble(distanceColumnName);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return distance;
	}

	public Map<Integer, String> getStationInformation() {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		HashMap<Integer, String> hashMapOfStation = new HashMap<Integer, String>();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareCall("{call getAllStation()}");
			resultSet = statement.executeQuery();
			while (resultSet.next()) {
				hashMapOfStation.put(resultSet.getInt(stationIdColumnName), resultSet.getString(stationNameColumnName));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return hashMapOfStation;
	}
}
