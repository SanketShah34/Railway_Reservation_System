package com.project.setup;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import com.project.database.DButilities;

@Component
@ComponentScan("com.code.service")
@ComponentScan("com.code.logic")
public class RouteDAO implements IRouteDAO {

	@Autowired
	DButilities dbUtilities;

	@Override
	public void save(IRoute route) {
		Connection conn = dbUtilities.estConnection();
		if (route.getRId() == 0) {
			try {
				CallableStatement stmt = conn.prepareCall("{call addRoute( ? , ? , ?)}");
				stmt.setInt(1, route.getSourceId());
				stmt.setInt(2, route.getDestinationId());
				stmt.setDouble(3, route.getDistance());

				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbUtilities.closeConnection(conn);
			}
		} else {
			try {
				CallableStatement stmt = conn.prepareCall("{call editRoute( ? , ? , ? , ?)}");
				stmt.setInt(1, route.getRId());
				stmt.setInt(2, route.getSourceId());
				stmt.setInt(3, route.getDestinationId());
				stmt.setDouble(4, route.getDistance());

				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				dbUtilities.closeConnection(conn);
			}
		}
	}

	@Override
	public List<Route> getAllRoute() {
		List<Route> listOfRoutes = new ArrayList<>();
		Connection conn = dbUtilities.estConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call getAllRoute()}");
			boolean hadResultsForList = stmt.execute();
			if (hadResultsForList) {
				ResultSet resultSet = stmt.getResultSet();
				while (resultSet.next()) {
					Route route = new Route();
					Station sourceStation = new Station();
					Station destinationStation = new Station();

					route.setRId(resultSet.getInt("rId"));

					sourceStation.setSId(resultSet.getInt("sourceStationId"));
					sourceStation.setStationName(resultSet.getString("sourceStationName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCode"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCity"));
					sourceStation.setStationState(resultSet.getString("sourceStationState"));

					route.setSource(sourceStation);
					route.setSourceId(sourceStation.sId);

					destinationStation.setSId(resultSet.getInt("destinationStationId"));
					destinationStation.setStationName(resultSet.getString("destinationStationName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCode"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCity"));
					destinationStation.setStationState(resultSet.getString("destinationStationState"));

					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.sId);

					route.setDistance(resultSet.getDouble("distance"));

					listOfRoutes.add(route);

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}

		return listOfRoutes;
	}

	@Override
	public IRoute getRoute(Integer rId) {
		Connection conn = dbUtilities.estConnection();

		IRoute route = new Route();
		Station sourceStation = new Station();
		Station destinationStation = new Station();
		try {
			CallableStatement stmt = conn.prepareCall("{call getRoute(?)}");
			stmt.setInt(1, rId);

			boolean hasRoute = stmt.execute();
			if (hasRoute) {
				ResultSet resultSet = stmt.getResultSet();
				if (resultSet.next()) {

					route.setRId(resultSet.getInt("rId"));

					sourceStation.setSId(resultSet.getInt("sourceStationId"));
					sourceStation.setStationName(resultSet.getString("sourceStationName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCode"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCity"));
					sourceStation.setStationState(resultSet.getString("sourceStationState"));

					route.setSource(sourceStation);
					route.setSourceId(sourceStation.sId);

					destinationStation.setSId(resultSet.getInt("destinationStationId"));
					destinationStation.setStationName(resultSet.getString("destinationStationName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCode"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCity"));
					destinationStation.setStationState(resultSet.getString("destinationStationState"));

					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.sId);

					route.setDistance(resultSet.getDouble("distance"));

				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}
		return route;
	}

	@Override
	public void deleteRoute(Integer rId) {
		Connection conn = dbUtilities.estConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call deleteRoute( ? )}");
			stmt.setInt(1, rId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}
	}

	@Override
	public IRoute getrouteByStation(int station1, int station2) {
	//	System.out.println("in getroute by station method");
		System.out.println("---source station-----"+station1+"---destination station ---"+station2);
		Connection conn = dbUtilities.estConnection();

		IRoute route = new Route();

		try {
			CallableStatement stmt = conn.prepareCall("{call getRoutebyStation(?, ?)}");
			stmt.setInt(1, station1);
			stmt.setInt(2, station2);

			boolean hasRoute = stmt.execute();
			if (hasRoute) {
				ResultSet resultSet = stmt.getResultSet();
				if (resultSet.next()) {

					route.setRId(resultSet.getInt("rId"));
					route.setDistance(resultSet.getDouble("distance"));
				//	System.out.println(resultSet.getDouble("distance"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			dbUtilities.closeConnection(conn);
		}

		// TODO Auto-generated method stub
		return route;
	}

}
