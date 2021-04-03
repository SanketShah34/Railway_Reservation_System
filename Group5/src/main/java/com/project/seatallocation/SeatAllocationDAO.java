package com.project.seatallocation;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;

public class SeatAllocationDAO implements ISeatAllocationDAO {

	public final String totalCoachesColumnName = "totalCoaches";
	public final String reservationIdColumnName = "reservationId";
	public final String coachNumberColumnName = "coachNumber";
	public final String seatNumberColumnName = "seatNumber";
	public final String startStationColumnName = "startStation";
	public final String middleStationsColumnName = "middleStations";
	public final String endStationColumnName = "endStation";
	public final String upperBerthPreference = "Upper Berth";
	public final String lowerBerthPreference = "Lower Berth";
	public final String noBerthPreference = "No Preference";
	
	@Override
	public IReservation allocateSeat(IReservation reservation) {
		
		int totalCoaches = 0;
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareCall("{call getTotalCoaches(?)}");
			statement.setInt(1, reservation.getTrainId());
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				 totalCoaches = resultSet.getInt(totalCoachesColumnName);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		
		if(0 < totalCoaches) {
			//int totalSeats = totalCoaches * 20;
			List<Integer> trainStations = getTrainStationsByTrainId(reservation);
			
			Map<String,Set<Integer>> trainCoachAndSeatsData = getReservedPassengerData(reservation, trainStations);
			
			List<IPassengerInformation> passengerInformation = reservation.getPassengerInformation();
			List<IPassengerInformation> newPassengerInformation = new ArrayList<>();
			for(int i=0; i<passengerInformation.size(); i++) {
				IPassengerInformation passengerInfo = passengerInformation.get(i);
				
				String berthPreference = passengerInfo.getBerthPreference();
				
				boolean seatAllocated = false;
				
				int middleCoach = totalCoaches/2;
				
				List<String> coachNumbers = new ArrayList<>();
				if(0 < totalCoaches%2) {
					coachNumbers.add(Character.toString((char) 65 + middleCoach));
					for(int k=middleCoach-1,l=middleCoach+1; k>=0 ; k--,l++) {
						coachNumbers.add(Character.toString((char) 65 + k));
						coachNumbers.add(Character.toString((char) 65 + l));
					}
				}
				else {
					for(int k=middleCoach-1,l=middleCoach; k>=0 ; k--,l++) {
						coachNumbers.add(Character.toString((char) 65 + k));
						coachNumbers.add(Character.toString((char) 65 + l));
					}
				}
				
				if(lowerBerthPreference.equals(berthPreference)) {
						
					int m=0;
					while(Boolean.FALSE.equals(seatAllocated) && m<coachNumbers.size()) {
						if(trainCoachAndSeatsData.containsKey(coachNumbers.get(m))) {
							int j = 1;
							while(j>0 && j<=32) {
								if(trainCoachAndSeatsData.get(coachNumbers.get(m)).contains(j)) {
									j+=2;
								}
								else {
									passengerInfo.setCoachNumber(coachNumbers.get(m));
									passengerInfo.setSeatNumber(j);
									newPassengerInformation.add(passengerInfo);
									trainCoachAndSeatsData.get(coachNumbers.get(m)).add(j);
									j = -1;
									seatAllocated = true;
								}
							}
						}
						else {
							Set<Integer> seatNumberSet = new HashSet<>();
							seatNumberSet.add(1);
							trainCoachAndSeatsData.put(coachNumbers.get(m), seatNumberSet);
							passengerInfo.setCoachNumber(coachNumbers.get(m));
							passengerInfo.setSeatNumber(1);
							newPassengerInformation.add(passengerInfo);
							seatAllocated = true;
						}
						m++;
					}
				}
				else if(upperBerthPreference.equals(berthPreference)) {
					int m=0;
					while(Boolean.FALSE.equals(seatAllocated) && m<coachNumbers.size()) {
						if(trainCoachAndSeatsData.containsKey(coachNumbers.get(m))) {
							int j = 2;
							while(j>0 && j<=32) {
								if(trainCoachAndSeatsData.get(coachNumbers.get(m)).contains(j)) {
									j+=2;
								}
								else {
									passengerInfo.setCoachNumber(coachNumbers.get(m));
									passengerInfo.setSeatNumber(j);
									newPassengerInformation.add(passengerInfo);
									trainCoachAndSeatsData.get(coachNumbers.get(m)).add(j);
									j = -1;
									seatAllocated = true;
								}
							}
						}
						else {
							Set<Integer> seatNumberSet = new HashSet<>();
							seatNumberSet.add(1);
							trainCoachAndSeatsData.put(coachNumbers.get(m), seatNumberSet);
							passengerInfo.setCoachNumber(coachNumbers.get(m));
							passengerInfo.setSeatNumber(1);
							newPassengerInformation.add(passengerInfo);
							seatAllocated = true;
						}
						m++;
					}
				}
				else if(noBerthPreference.equals(berthPreference) || Boolean.FALSE.equals(seatAllocated)) {
					int m=0;
					while(Boolean.FALSE.equals(seatAllocated) && m<coachNumbers.size()) {
						if(trainCoachAndSeatsData.containsKey(coachNumbers.get(m))) {
							int j = 1;
							while(j>0 && j<=32) {
								if(trainCoachAndSeatsData.get(coachNumbers.get(m)).contains(j)) {
									j+=1;
								}
								else {
									passengerInfo.setCoachNumber(coachNumbers.get(m));
									passengerInfo.setSeatNumber(j);
									newPassengerInformation.add(passengerInfo);
									trainCoachAndSeatsData.get(coachNumbers.get(m)).add(j);
									j = -1;
									seatAllocated = true;
								}
							}
						}
						else {
							Set<Integer> seatNumberSet = new HashSet<>();
							seatNumberSet.add(1);
							trainCoachAndSeatsData.put(coachNumbers.get(m), seatNumberSet);
							passengerInfo.setCoachNumber(coachNumbers.get(m));
							passengerInfo.setSeatNumber(1);
							newPassengerInformation.add(passengerInfo);
							seatAllocated = true;
						}
						m++;
					}
				}
			}
			reservation.setPassengerInformation(newPassengerInformation);
		}
		return reservation;
	}

	private List<Integer> getTrainStationsByTrainId(IReservation reservation) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		List<Integer> stationIds = new ArrayList<Integer>();
		try {
			statement = connection.prepareCall("{call getTrain(?)}");
			statement.setInt(1, reservation.getTrainId());
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				stationIds.add(resultSet.getInt(startStationColumnName));
				String[] middleStationsList = resultSet.getString(middleStationsColumnName).split(",");
				for(String middleStation : middleStationsList) {
					stationIds.add(Integer.valueOf(middleStation));
				}
				stationIds.add(resultSet.getInt(endStationColumnName));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if(null != resultSet) {
				databaseUtilities.closeResultSet(resultSet);
			}
			if(null != statement) {
				databaseUtilities.closeStatement(statement);
			}
			if(null != connection) {
				databaseUtilities.closeConnection(connection);
			}
		}
		return stationIds;
	}

	private Map<String,Set<Integer>> getReservedPassengerData(IReservation reservation, List<Integer> trainStations) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		CallableStatement anotherStatement = null;
		ResultSet anotherResultSet = null;
		CallableStatement statementForEndStations = null;
		ResultSet resultSetForEndStations = null;
		List<Integer> reservationIds = new ArrayList<Integer>();
		Map<String,Set<Integer>> trainCoachesData = new HashMap<>();
		try {
			
			List<Integer> newAllStationsListForReservation = new ArrayList<>();
			int startStationIndex = trainStations.indexOf(reservation.getSourceStationId());
			int destinationStationIndex = trainStations.indexOf(reservation.getDestinationStationId());
			newAllStationsListForReservation.addAll(trainStations.subList(startStationIndex, destinationStationIndex+1));
			System.out.println("Size : "+newAllStationsListForReservation.size());
			if(2 < newAllStationsListForReservation.size()) {
				for(int i=0; i<newAllStationsListForReservation.size()-1; i++) {
					System.out.println("i:"+i);
					statement = connection.prepareCall("{call getReservationIdFromTrainIdAndStartDateAndStations( ?, ?, ?, ?)}");
					statement.setInt(1, reservation.getTrainId());
					statement.setDate(2, reservation.getStartDate());
					statement.setInt(3, newAllStationsListForReservation.get(i));
					statement.setInt(4, newAllStationsListForReservation.get(i+1));
					resultSet = statement.executeQuery();
					while(resultSet.next()) {
						reservationIds.add(resultSet.getInt(reservationIdColumnName));
					}
				}
			}
			statementForEndStations = connection.prepareCall("{call getReservationIdFromTrainIdAndStartDateAndStations( ?, ?, ?, ?)}");
			statementForEndStations.setInt(1, reservation.getTrainId());
			statementForEndStations.setDate(2, reservation.getStartDate());
			statementForEndStations.setInt(3, newAllStationsListForReservation.get(0));
			statementForEndStations.setInt(4, newAllStationsListForReservation.get(newAllStationsListForReservation.size()-1));
			System.out.println(statementForEndStations);
			resultSetForEndStations = statementForEndStations.executeQuery();
			while(resultSetForEndStations.next()) {
				reservationIds.add(resultSetForEndStations.getInt(reservationIdColumnName));
			}
			
			for(int i=0; i<reservationIds.size(); i++) {
				
				anotherStatement = connection.prepareCall("{call getReservedPassengerData(?)}");
				anotherStatement.setInt(1, reservationIds.get(i));
				anotherResultSet = anotherStatement.executeQuery();

				while(anotherResultSet.next()) {
					if(trainCoachesData.containsKey(anotherResultSet.getString(coachNumberColumnName))){
						Set<Integer> seatNumberSet = new HashSet<>();
						seatNumberSet = trainCoachesData.get(anotherResultSet.getString(coachNumberColumnName));
						seatNumberSet.add(anotherResultSet.getInt(seatNumberColumnName));
						trainCoachesData.replace(anotherResultSet.getString(coachNumberColumnName), seatNumberSet);
					}
					else {
						Set<Integer> seatNumberSet = new HashSet<>();
						seatNumberSet.add(anotherResultSet.getInt(seatNumberColumnName));
						trainCoachesData.put(anotherResultSet.getString(coachNumberColumnName), seatNumberSet);
					}
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			if(null != resultSet) {
				databaseUtilities.closeResultSet(resultSet);
			}
			if(null != statement) {
				databaseUtilities.closeStatement(statement);
			}
			if(null != anotherResultSet) {
				databaseUtilities.closeResultSet(anotherResultSet);
			}
			if(null != anotherStatement) {
				databaseUtilities.closeStatement(anotherStatement);
			}
			if(null != resultSetForEndStations) {
				databaseUtilities.closeResultSet(resultSetForEndStations);
			}
			if(null != statementForEndStations) {
				databaseUtilities.closeStatement(statementForEndStations);
			}
			if(null != anotherStatement) {
				databaseUtilities.closeStatement(anotherStatement);
			}
			if(null != connection) {
				databaseUtilities.closeConnection(connection);
			}
		}
		
		return trainCoachesData;
	}	
}