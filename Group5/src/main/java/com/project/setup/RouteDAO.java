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
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;

@Component

public class RouteDAO implements IRouteDAO {
	@Override
	public void save(IRoute route) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();
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
				databaseUtilities.closeConnection(conn);
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
				databaseUtilities.closeConnection(conn);
			}
		}
	}

	@Override
	public List<Route> getAllRoute() {
		List<Route> listOfRoutes = new ArrayList<>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();
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
			databaseUtilities.closeConnection(conn);
		}

		return listOfRoutes;
	}

	@Override
	public IRoute getRoute(Integer rId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();

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
			databaseUtilities.closeConnection(conn);
		}
		return route;
	}

	@Override
	public void deleteRoute(Integer rId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call deleteRoute( ? )}");
			stmt.setInt(1, rId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseUtilities.closeConnection(conn);
		}
	}

	@Override
	public IRoute getrouteByStation(int station1, int station2) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();

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
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			databaseUtilities.closeConnection(conn);
		}

		// TODO Auto-generated method stub
		return route;
	}

}
