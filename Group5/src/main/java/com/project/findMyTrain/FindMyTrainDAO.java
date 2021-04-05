package com.project.findMyTrain;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

public class FindMyTrainDAO implements IFindMyTrainDAO {
	
	 public ITrain getLiveTrainStatus(int trainCode, Date startDate) {
	        SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
	        ITrain train = setupAbstractFactory.createNewTrain();
	        DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
	        IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
	        Connection connection = databaseUtilities.establishConnection();
	        CallableStatement statement = null;
	        ResultSet resultSet = null;
	        try {
	            statement = connection.prepareCall("{call getTrain(?)}");
	            statement.setInt(1, trainCode);
	            resultSet = statement.executeQuery();
	            while(resultSet.next()) {
	                train.setDays(resultSet.getString("days"));
	                train.setDepartureTime(resultSet.getString("departureTime"));
	                train.setStartStation(resultSet.getString("startStation"));
	                train.setMiddleStations(resultSet.getString("middleStations"));
	                train.setEndStation(resultSet.getString("endStation"));
	            }
	        } catch (SQLException exception) {
	            exception.printStackTrace();
	        } finally {
	            databaseUtilities.closeResultSet(resultSet);
	            databaseUtilities.closeStatement(statement);
	            databaseUtilities.closeConnection(connection);
	        }
	       return train;
	        /*List<Integer> stationIds = new ArrayList<Integer>();
	        stationIds.add(Integer.valueOf(train.getStartStation()));
	        String[] middleStationsList = train.getMiddleStations().split(",");
	        for(String middleStation : middleStationsList) {
	            stationIds.add(Integer.valueOf(middleStation));
	        }
	        stationIds.add(Integer.valueOf(train.getEndStation()));
	       
	        double totalDistance =0;
	       
	        List<DistanceData> distanceData = new ArrayList<>();
	        for(int i=0; i<stationIds.size()-1; i++) {
	            double distance = getRouteInfo(stationIds.get(i), stationIds.get(i+1));
	            totalDistance+= distance;
	            DistanceData tempDistanceData = new DistanceData();
	            tempDistanceData.setStartStation(stationIds.get(i));
	            tempDistanceData.setEndStation(stationIds.get(i+1));
	            tempDistanceData.setDistance(distance);
	            distanceData.add(tempDistanceData);
	        }
	       
	        Instant instant = startDate.toInstant();
	        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
	        LocalDate date = zdt.toLocalDate();
	       
	        DayOfWeek day = date.getDayOfWeek();
	        System.out.println(day);
	       
	        if(train.getDays().contains(day.getDisplayName(TextStyle.FULL, new Locale("EN")))) {
	            LocalDateTime currentDateTime = LocalDateTime.now();
	           
	            String departureTimeOfTrain = train.getDepartureTime();
	            LocalTime departureTime = LocalTime.parse(departureTimeOfTrain);
	           
	            LocalDateTime trainDepartureDateTime = LocalDateTime.of(date, departureTime);
	           
	            long minutes = ChronoUnit.MINUTES.between(currentDateTime, trainDepartureDateTime);
	           
	            long totalMinutesFromTotalDistance = Long.valueOf(String.valueOf(totalDistance * 1.5)) + ((distanceData.size()-1) * 5);
	           
	            if(totalMinutesFromTotalDistance <= minutes) {
	                System.out.println("Train already reached at destination.");
	            }
	            else if(0 >= minutes) {
	                System.out.println("Train has not departed from source.");
	            }
	            else {
	                long tempMinutes = 0;
	                for(int i=0; i< distanceData.size(); i++) {
	                    tempMinutes +=  Long.valueOf(String.valueOf(distanceData.get(i).getDistance() * 1.5)) + 5;
	                    if(tempMinutes > minutes) {
	                        System.out.println("Train is between station : "+distanceData.get(i).getStartStation()
	                                +" and station : "+distanceData.get(i).getEndStation());
	                    }
	                }
	            }
	        }
	        else {
	            System.out.println("No train running on that day.");
	        }*/
	    }
	   
	    public double getRouteInformation(Integer startStation, Integer endStation) {
	        DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
	        IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
	        Connection connection = databaseUtilities.establishConnection();
	        CallableStatement statement = null;
	        ResultSet resultSet = null;
	        double distance = 0;
	        try {
	            statement = connection.prepareCall("{call getRoutebyStation(?, ?)}");
	            statement.setInt(1, startStation);
	            statement.setInt(1, endStation);
	            resultSet = statement.executeQuery();
	            while(resultSet.next()) {
	                distance = resultSet.getDouble("distance");
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

}
