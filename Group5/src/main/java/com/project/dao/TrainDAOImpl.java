package com.project.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.project.entity.Train;
import com.project.service.DButilities;

@Component
@ComponentScan("com.code.service")
public class TrainDAOImpl implements TrainDAO {

	@Autowired
	DButilities dbUtilities;
	
	@Override
	public List<Train> getAllTrain() {
		List<Train> listOfTrain = new ArrayList<Train>();
		listOfTrain.removeAll(listOfTrain);
		Connection conn = dbUtilities.estConnection();

		try {
			CallableStatement stmt = conn.prepareCall("{call getAllTrain()}");

			boolean hasResultsForList = stmt.execute();

			if (hasResultsForList) {

				ResultSet resultSet = stmt.getResultSet();
				while (resultSet.next()) {
					Train train = new Train();
					train.setTrainId(resultSet.getInt(1));
					train.setTrainCode(resultSet.getInt(2));
					train.setTrainName(resultSet.getString(3));
					train.setTrainType(resultSet.getString(4));
					train.setDays(resultSet.getString(5));
					train.setDepartureTime(resultSet.getString(6));
					train.setTotalCoaches(resultSet.getInt(7));
					train.setStartStation(resultSet.getString(8));
					train.setMiddleStations(resultSet.getString(9));
					train.setEndStation(resultSet.getString(10));

					String middleStationString = "";
					String[] middleStations = resultSet.getString(9).split(",");
					for (String midd : middleStations) {
						int middle = Integer.parseInt(midd);
						CallableStatement stmt1 = conn.prepareCall("{call getMiddleStation(?)}");
						stmt1.setInt(1, middle);
						boolean hasResultsForList1 = stmt1.execute();

						if (hasResultsForList1) {

							ResultSet resultSet1 = stmt1.getResultSet();
							while (resultSet1.next()) {
								middleStationString += resultSet1.getString(1)+",";
							}
						}
					}
					train.setMiddleStations(middleStationString);
					listOfTrain.add(train);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}
		return listOfTrain;
	}

	@Override
	public boolean saveTrain(Train train) {
		Connection conn = dbUtilities.estConnection();
		
		boolean validRoute = validateRoutes(conn, train);
		
		if(validRoute) {
			if(train.getTrainId() == 0) {
				System.out.println("in add new ");
				try {
					CallableStatement stmt = conn.prepareCall("{call addTrain( ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
					stmt.setInt(1, train.getTrainCode());
					stmt.setString(2, train.getTrainName());
					stmt.setString(3, train.getTrainType());
					stmt.setString(4, train.getDays());
					stmt.setString(5, train.getDepartureTime());
					stmt.setInt(6, train.getTotalCoaches());
					stmt.setInt(7, Integer.parseInt(train.getStartStation()));
					stmt.setString(8, train.getMiddleStations());
					stmt.setInt(9, Integer.parseInt(train.getEndStation()));
	
					stmt.execute();
	
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			else {
				System.out.println("in edit");
				try {
					CallableStatement stmt = conn.prepareCall("{call editTrain( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}");
					stmt.setInt(1, train.getTrainId());
					stmt.setInt(2, train.getTrainCode());
					stmt.setString(3, train.getTrainName());
					stmt.setString(4, train.getTrainType());
					stmt.setString(5, train.getDays());
					stmt.setString(6, train.getDepartureTime());
					stmt.setInt(7, train.getTotalCoaches());
					stmt.setInt(8, Integer.parseInt(train.getStartStation()));
					stmt.setString(9, train.getMiddleStations());
					stmt.setInt(10, Integer.parseInt(train.getEndStation()));
	
					stmt.execute();
	
				} catch (SQLException e) {
					e.printStackTrace();
				} finally {
					dbUtilities.closeConnection(conn);
				}
			}
			
			return true;
		}
		else {
			return false;
		}
	}

	private boolean validateRoutes(Connection conn, Train train) {
		List<Integer> allStations = new ArrayList<>();
		
		allStations.add(Integer.parseInt(train.getStartStation()));
		
		String[] middleStationsList = train.getMiddleStations().split(",");
		for(String mid : middleStationsList) {
			allStations.add(Integer.parseInt(mid));
		}
		
		allStations.add(Integer.parseInt(train.getEndStation()));
		
		allStations.forEach( a -> System.out.println(a));
		
		boolean routeFound = true;
		
		for(int i=0; i<allStations.size()-1; i++) {
			CallableStatement stmt;
			try {
				stmt = conn.prepareCall("{call checkRoute( ?, ?)}");
				stmt.setInt(1, allStations.get(i));
				stmt.setInt(2, allStations.get(i+1));
				
				boolean hasResultsForList1 = stmt.execute();

				if (hasResultsForList1) {

					ResultSet resultSet = stmt.getResultSet();
					int routeId = -1 ;
					while (resultSet.next()) {
						routeId = resultSet.getInt(1);
					}
					
					if(routeId > 0) {
						routeFound = true;
						System.out.println("found for "+allStations.get(i)+" and "+allStations.get(i+1));
					}
					else {
						routeFound = false;
						break;
					}
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbUtilities.closeConnection(conn);
			}
		}
		
		return routeFound;
	}

	@Override
	public Train getTrain(Integer trainId) {
		Connection conn = dbUtilities.estConnection();
		
		Train train = new Train();
		try {
			CallableStatement stmt = conn.prepareCall("{call getTrain(?)}");
			stmt.setInt(1, trainId);
			
			boolean hasResult = stmt.execute();

			if (hasResult) {
				ResultSet resultSet = stmt.getResultSet();
				while (resultSet.next()) {
					train.setTrainId(resultSet.getInt(1));
					train.setTrainCode(resultSet.getInt(2));
					train.setTrainName(resultSet.getString(3));
					train.setTrainType(resultSet.getString(4));
					train.setDays(resultSet.getString(5));
					train.setDepartureTime(resultSet.getString(6));
					train.setTotalCoaches(resultSet.getInt(7));
					train.setStartStation(String.valueOf(resultSet.getInt(8)));
					train.setMiddleStations(resultSet.getString(9));
					train.setEndStation(String.valueOf(resultSet.getInt(10)));
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}
		return train;
	}

	@Override
	public void deleteTrain(Integer trainId) {
		Connection conn = dbUtilities.estConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call deleteTrain( ? )}");
			stmt.setInt(1, trainId);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}
	}

}
