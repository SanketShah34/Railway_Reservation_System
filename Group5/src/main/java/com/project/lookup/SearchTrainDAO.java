package com.project.lookup;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

@Component
public class SearchTrainDAO implements ISearchTrainDAO {

	public final String trainIdColumnName = "trainId";
	public final String trainCodeColumnName = "trainCode";
	public final String trainNameColumnName = "trainName";
	public final String trainTypeColumnName = "trainType";
	public final String daysColumnName = "days";
	public final String departureTimeColumnName = "departureTime";
	public final String totalCoachesColumnName = "totalCoaches";
	public final String startStationColumnName = "startStation";
	public final String middleStationsColumnName = "middleStations";
	public final String endStationColumnName = "endStation";

	public List<ITrain> searchTrains(ISearchTrain searchTrain) {
		List<ITrain> trains = new ArrayList<ITrain>(0);
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareCall("{call searchTrain(? ,? , ? , ? , ?)}");
			statement.setInt(1, Integer.parseInt(searchTrain.getSourceStation()));
			statement.setInt(2, Integer.parseInt(searchTrain.getDestinationStation()));
			String searchStringForSourceStation = '%' + searchTrain.getSourceStation() + '%';
			String searchStringForDestinationStation = '%' + searchTrain.getDestinationStation() + '%';
			statement.setString(3, searchStringForSourceStation);
			statement.setString(4, searchStringForDestinationStation);
			statement.setString(5, searchTrain.getTrainType());
			boolean hadResultsForList = statement.execute();
			if (hadResultsForList) {
				resultSet = statement.getResultSet();
				while (resultSet.next()) {
					List<Integer> allStations = new ArrayList<Integer>();
					ITrain train = setupAbstractFactory.createNewTrain();
					train.setTrainId(resultSet.getInt(trainIdColumnName));
					train.setTrainCode(resultSet.getInt(trainCodeColumnName));
					train.setTrainName(resultSet.getString(trainNameColumnName));
					train.setTrainType(resultSet.getString(trainTypeColumnName));
					train.setDays(resultSet.getString(daysColumnName));
					train.setDepartureTime(resultSet.getString(departureTimeColumnName));
					train.setTotalCoaches(resultSet.getInt(totalCoachesColumnName));
					train.setStartStation(resultSet.getString(startStationColumnName));
					allStations.add(Integer.parseInt(train.getStartStation()));
					train.setMiddleStations(resultSet.getString(middleStationsColumnName));
					if (train.getMiddleStations() == null) {

					} else {
						String[] middleStationsList = train.getMiddleStations().split(",");
						for (String mid : middleStationsList) {
							allStations.add(Integer.parseInt(mid));
						}
					}
					train.setEndStation(resultSet.getString(endStationColumnName));
					allStations.add(Integer.parseInt(train.getEndStation()));
					train.setTotalStation(allStations);
					trains.add(train);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return trains;
	}

	public String getDaysNameFromDate(Date dateToBeformate) {
		final Date currentTime = dateToBeformate;
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		return simpleDateformat.format(currentTime);
	}

}
