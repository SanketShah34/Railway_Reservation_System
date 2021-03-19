package com.project.lookup;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

@Component
public class SearchTrainDAO implements ISearchTrainDAO {
	
	@Override
	public List<ITrain> searchTrains(ISearchTrain searchTrain) {
		
		List<ITrain> trains = new ArrayList<ITrain>();
//		Integer count = 0;
		
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		
		Connection connection = databaseUtilities.establishConnection();	
		CallableStatement statement = null;
		ResultSet resultSet = null;
		
		try {
			statement = connection.prepareCall("{call searchTrain(? ,? , ? , ? , ?, ?)}");
			statement.setInt(1, Integer.parseInt(searchTrain.getSourceStation()));
			statement.setInt(2, Integer.parseInt(searchTrain.getDestinationStation()));
			
			String dayName = getDaysNameFromDate(searchTrain.getDateofJourny());
			String searchStringForSourceStation = '%'+searchTrain.getSourceStation()+'%';
			String searchStringForDestinationStation = '%'+searchTrain.getDestinationStation()+'%';
			String daysToBeSearch = '%'+dayName+'%';
			
			statement.setString(3, searchStringForSourceStation);
			statement.setString(4, searchStringForDestinationStation);
			statement.setString(5, searchTrain.getTrainType());
			statement.setString(6, daysToBeSearch);
			
			boolean hadResultsForList = statement.execute();

			if (hadResultsForList) {
				resultSet = statement.getResultSet();
				while (resultSet.next()) {
					List<Integer> allStations = new ArrayList<Integer>();
					ITrain train = setupAbstractFactory.createNewTrain();
					train.setTrainId(resultSet.getInt("trainId"));
					train.setTrainCode(resultSet.getInt("trainCode"));
					train.setTrainName(resultSet.getString("trainName"));
					train.setTrainType(resultSet.getString("trainType"));
					train.setDays(resultSet.getString("days"));
					train.setDepartureTime(resultSet.getString("departureTime"));
					train.setTotalCoaches(resultSet.getInt("totalCoaches"));
					train.setStartStation(resultSet.getString("startStation"));
					allStations.add(Integer.parseInt(train.getStartStation()));
					train.setMiddleStations(resultSet.getString("middleStations"));
					if(train.getMiddleStations() == null) {
						
					}
					else {
						String[] middleStationsList = train.getMiddleStations().split(",");
						for(String mid : middleStationsList) {
							allStations.add(Integer.parseInt(mid));
						}
					}
					train.setEndStation(resultSet.getString("endStation"));
					allStations.add(Integer.parseInt(train.getEndStation()));
					train.setTotalStation(allStations);
					trains.add(train);
//					count++;
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
		SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE");
		return simpleDateformat.format(currentTime);
	}

}
