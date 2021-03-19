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
	public void saveRoute(IRoute route){
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		if (route.getRouteId() == 0) {
			try {
				statement = connection.prepareCall("{call addRoute( ? , ? , ?)}");
				statement.setInt(1, route.getSourceId());
				statement.setInt(2, route.getDestinationId());
				statement.setDouble(3, route.getDistance());

				statement.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				databaseUtilities.closeConnection(connection);
			}
		} else {
			try {
				statement = connection.prepareCall("{call editRoute( ? , ? , ? , ?)}");
				statement.setInt(1, route.getRouteId());
				statement.setInt(2, route.getSourceId());
				statement.setInt(3, route.getDestinationId());
				statement.setDouble(4, route.getDistance());

				statement.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				databaseUtilities.closeStatement(statement);
				databaseUtilities.closeConnection(connection);
			}
		}
	}

	@Override
	public List<IRoute> getAllRoute() {
		List<IRoute> listOfRoutes = new ArrayList<>();
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

					route.setRouteId(resultSet.getInt("routeId"));

					sourceStation.setStationId(resultSet.getInt("sourceStationId"));
					sourceStation.setStationName(resultSet.getString("sourceStationName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCode"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCity"));
					sourceStation.setStationState(resultSet.getString("sourceStationState"));

					route.setSource(sourceStation);
					route.setSourceId(sourceStation.stationId);

					destinationStation.setStationId(resultSet.getInt("destinationStationId"));
					destinationStation.setStationName(resultSet.getString("destinationStationName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCode"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCity"));
					destinationStation.setStationState(resultSet.getString("destinationStationState"));

					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.stationId);

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
	public IRoute getRoute(Integer routeId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();

		IRoute route = new Route();
		Station sourceStation = new Station();
		Station destinationStation = new Station();
		try {
			CallableStatement stmt = conn.prepareCall("{call getRoute(?)}");
			stmt.setInt(1, routeId);

			boolean hasRoute = stmt.execute();
			if (hasRoute) {
				ResultSet resultSet = stmt.getResultSet();
				if (resultSet.next()) {

					route.setRouteId(resultSet.getInt("routeId"));

					sourceStation.setStationId(resultSet.getInt("sourceStationId"));
					sourceStation.setStationName(resultSet.getString("sourceStationName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCode"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCity"));
					sourceStation.setStationState(resultSet.getString("sourceStationState"));

					route.setSource(sourceStation);
					route.setSourceId(sourceStation.stationId);

					destinationStation.setStationId(resultSet.getInt("destinationStationId"));
					destinationStation.setStationName(resultSet.getString("destinationStationName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCode"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCity"));
					destinationStation.setStationState(resultSet.getString("destinationStationState"));

					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.stationId);

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
	public IRoute getRouteByStation(int station1, int station2) {
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

					route.setRouteId(resultSet.getInt("routeId"));
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
