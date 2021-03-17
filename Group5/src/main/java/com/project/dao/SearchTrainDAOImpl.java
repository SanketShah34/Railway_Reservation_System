package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.project.database.DButilities;
import com.project.entity.SearchTrain;
import com.project.setup.Train;
import com.project.database.DButilities;

import ch.qos.logback.core.subst.Token.Type;


@Component
@ComponentScan("com.code.database")
public class SearchTrainDAOImpl implements SearchTrainDAO {


	@Autowired
	DButilities dbUtilities;
	
	@Override
	public List<Train> searchTrains(SearchTrain searchTrain) {
		System.out.println("in search train DAO impl");
		List<Train> trains = new ArrayList();
		
		Integer count = 0;
		Connection conn = dbUtilities.estConnection();	
		
		try {
			CallableStatement stmt = conn.prepareCall("{call searchTrain(? ,? , ? , ? , ?, ?)}");
			stmt.setInt(1, Integer.parseInt(searchTrain.getSourceStation()));
			stmt.setInt(2, Integer.parseInt(searchTrain.getDestinationStation()));
			
			String dayName = getDaysNameFromDate(searchTrain.getDateofJourny());
			String searchStringForSourceStation = '%'+searchTrain.getSourceStation()+'%';
			String searchStringForDestinationStation = '%'+searchTrain.getDestinationStation()+'%';
			String daysToBeSearch = '%'+dayName+'%';
			
			
			
			System.out.println("-----"+dayName);
			
			stmt.setString(3, searchStringForSourceStation);
			stmt.setString(4, searchStringForDestinationStation);
			stmt.setString(5, searchTrain.getTrainType());
			stmt.setString(6, daysToBeSearch);
			
			boolean hadResultsForList = stmt.execute();

			if (hadResultsForList) {
				
				ResultSet resultSet = stmt.getResultSet();
				while (resultSet.next()) {
				//	System.out.println("for train -------------------");
					Train train = new Train();
					int count2  = 0 ;
					
					List<Integer> allStations = new ArrayList();
					
					
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
							count2++;
							allStations.add(Integer.parseInt(mid));
						//	System.out.println("count"+count2);
						}
					}
					
				//	System.out.println("size after aading middle one "+allStations.size());
					
					train.setEndStation(resultSet.getString("endStation"));
					allStations.add(Integer.parseInt(train.getEndStation()));
				//	System.out.println("size after aading thired one "+allStations.size());
					
					train.setTotalStation(allStations);
					
					trains.add(train);
					count++;
				}
			}
		//	System.out.println(count);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}

		// TODO Auto-generated method stub
		return trains;
	}
	
	public String getDaysNameFromDate(Date dateToBeformate) {
		
		final Date currentTime = dateToBeformate;
		SimpleDateFormat  simpleDateformat = new SimpleDateFormat("EEEE"); // the day of the week spelled out completely
		return simpleDateformat.format(currentTime);
	}

}
