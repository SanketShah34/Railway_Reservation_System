package com.project.cancelTrain;

import com.project.setup.ICancelTrain;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IReservation;

public class trainCancellationDAO {

	public final static String trainIdColumnName = "trainId";
	
	public void fetchAllReservations(ICancelTrain cancelTrain) {
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
				statement.setString(2, cancelTrain.localDateToString());
			}
			
			
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
	}
}
