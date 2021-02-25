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

import com.project.entity.Route;
import com.project.entity.Station;
import com.project.service.DButilities;

@Component
@ComponentScan("com.code.service")
public class RouteDAO implements IRouteDAO {
	
	@Autowired
	DButilities dbutilities;

	@Override
	public void save(Route route) {
		Connection conn = dbutilities.EstConnection();
		if(route.getrId() == 0) {
			try {
				CallableStatement stmt = conn.prepareCall("{call addRoute( ? , ? , ?)}");
				stmt.setInt(1, route.getSourceId());
				stmt.setInt(2, route.getDestinationId());
				stmt.setDouble(3, route.getDistance());
				
				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbutilities.closeConnection(conn);
			}
		}
		else {
			System.out.println("in edit");
			try {
				CallableStatement stmt = conn.prepareCall("{call editRoute( ? , ? , ? , ?)}");
				System.out.println(route);
				stmt.setInt(1, route.getrId());
				stmt.setInt(2, route.getSourceId());
				stmt.setInt(3, route.getDestinationId());
				stmt.setDouble(4, route.getDistance());

				stmt.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				dbutilities.closeConnection(conn);
			}
		}
		
	}

	@Override
	public List<Route> getAllRoute() {
		List<Route> listOfRoutes = new ArrayList<>();
		// TODO Auto-generated method stub
		Connection conn = dbutilities.EstConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call getAllRoute()}");

			boolean hadResultsForList = stmt.execute();

			if (hadResultsForList) {
				
				ResultSet resultSet = stmt.getResultSet();
				while (resultSet.next()) {
					Route route = new Route();
					Station sourceStation = new Station();
					Station destinationStation = new Station();
					
					route.setrId(resultSet.getInt("rid"));
					
					sourceStation.setSid(resultSet.getInt("sourceStationId"));
					sourceStation.setStationName(resultSet.getString("sourceStationName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCode"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCity"));
					sourceStation.setStationState(resultSet.getString("sourceStationState"));
						
					route.setSource(sourceStation);
					route.setSourceId(sourceStation.sid);
					
					destinationStation.setSid(resultSet.getInt("destinationStationId"));
					destinationStation.setStationName(resultSet.getString("destinationStationName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCode"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCity"));
					destinationStation.setStationState(resultSet.getString("destinationStationState"));
					
					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.sid);
					
					route.setDistance(resultSet.getDouble("distance"));
				 
					listOfRoutes.add(route);
				
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbutilities.closeConnection(conn);
		}

		return listOfRoutes;
	}

	@Override
	public Route getRoute(Integer rId) {
		// TODO Auto-generated method stub
		Connection conn = dbutilities.EstConnection();
		
		Route route = new Route();
		Station sourceStation = new Station();
		Station destinationStation = new Station();
		try {
			CallableStatement stmt = conn.prepareCall("{call getRoute(?)}");
			stmt.setInt(1, rId);
			
			boolean hasRoute = stmt.execute();
			if (hasRoute) {
				ResultSet resultSet = stmt.getResultSet();
				if (resultSet.next()) {

					route.setrId(resultSet.getInt("rId"));
					
					sourceStation.setSid(resultSet.getInt("sourceStationId"));
					sourceStation.setStationName(resultSet.getString("sourceStationName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCode"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCity"));
					sourceStation.setStationState(resultSet.getString("sourceStationState"));
						
					route.setSource(sourceStation);
					route.setSourceId(sourceStation.sid);
					
					destinationStation.setSid(resultSet.getInt("destinationStationId"));
					destinationStation.setStationName(resultSet.getString("destinationStationName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCode"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCity"));
					destinationStation.setStationState(resultSet.getString("destinationStationState"));
					
					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.sid);
					
					route.setDistance(resultSet.getDouble("distance"));
				 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			dbutilities.closeConnection(conn);
		}
		// TODO Auto-generated method stub
		return route;
	}

	@Override
	public void deleteRoute(Integer rId) {
		Connection conn = dbutilities.EstConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call deleteRoute( ? )}");
			stmt.setInt(1, rId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbutilities.closeConnection(conn);
		}
	}
	
	
}
