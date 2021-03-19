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
	public final String routeIdColumnName = "routeId";
	public final String sourceStationIdColumnName = "sourceStationId";
	public final String sourceStationNameColumnName = "sourceStationName";
	public final String sourceStationCodeColumnName = "sourceStationCode";
	public final String sourceStationCityColumnName = "sourceStationCity";
	public final String sourceStationStateColumnName = "sourceStationState";
	public final String destinationStationIdColumnName = "destinationStationId";
	public final String destinationStationNameColumnName = "destinationStationName";
	public final String destinationStationCodeColumnName = "destinationStationCode";
	public final String destinationStationCityColumnName = "destinationStationCity";
	public final String destinationStationStateColumnName = "destinationStationState";
	public final String distanceColumnName = "distance";
	
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

			} catch (SQLException exception) {
				exception.printStackTrace();
			} finally {
				databaseUtilities.closeStatement(statement);
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

			} catch (SQLException exception) {
				exception.printStackTrace();
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
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareCall("{call getAllRoute()}");
			boolean hadResult = statement.execute();
			if (hadResult) {
				resultSet = statement.getResultSet();
				
				while (resultSet.next()) {
					IRoute route = setupAbstractFactory.createNewRoute();
					IStation sourceStation = setupAbstractFactory.createNewStation();
					IStation destinationStation = setupAbstractFactory.createNewStation();

					route.setRouteId(resultSet.getInt("routeIdColumnName"));

					sourceStation.setStationId(resultSet.getInt("sourceStationIdColumnName"));
					sourceStation.setStationName(resultSet.getString("sourceStationNameColumnName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCodeColumnName"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCityColumnName"));
					sourceStation.setStationState(resultSet.getString("sourceStationStateColumnName"));

					route.setSource(sourceStation);
					route.setSourceId(sourceStation.getStationId());

					destinationStation.setStationId(resultSet.getInt("destinationStationIdColumnName"));
					destinationStation.setStationName(resultSet.getString("destinationStationNameColumnName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCodeColumnName"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCityColumnName"));
					destinationStation.setStationState(resultSet.getString("destinationStationStateColumnName"));

					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.getStationId());

					route.setDistance(resultSet.getDouble(distanceColumnName));

					listOfRoutes.add(route);

				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}

		return listOfRoutes;
	}

	@Override
	public IRoute getRoute(Integer routeId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRoute route = setupAbstractFactory.createNewRoute();
		IStation sourceStation = setupAbstractFactory.createNewStation();
		IStation destinationStation = setupAbstractFactory.createNewStation();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			CallableStatement stmt = connection.prepareCall("{call getRoute(?)}");
			stmt.setInt(1, routeId);

			boolean hasRoute = statement.execute();
			if (hasRoute) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {

					route.setRouteId(resultSet.getInt("routeIdColumnName"));

					sourceStation.setStationId(resultSet.getInt("sourceStationIdColumnName"));
					sourceStation.setStationName(resultSet.getString("sourceStationNameColumnName"));
					sourceStation.setStationCode(resultSet.getString("sourceStationCodeColumnName"));
					sourceStation.setStationCity(resultSet.getString("sourceStationCityColumnName"));
					sourceStation.setStationState(resultSet.getString("sourceStationStateColumnName"));

					route.setSource(sourceStation);
					route.setSourceId(sourceStation.getStationId());

					destinationStation.setStationId(resultSet.getInt("destinationStationIdColumnName"));
					destinationStation.setStationName(resultSet.getString("destinationStationNameColumnName"));
					destinationStation.setStationCode(resultSet.getString("destinationStationCodeColumnName"));
					destinationStation.setStationCity(resultSet.getString("destinationStationCityColumnName"));
					destinationStation.setStationState(resultSet.getString("destinationStationStateColumnName"));

					route.setDestination(destinationStation);
					route.setDestinationId(destinationStation.getStationId());

					route.setDistance(resultSet.getDouble(distanceColumnName));

				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return route;
	}

	@Override
	public void deleteRoute(Integer routeId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		try {
			statement = connection.prepareCall("{call deleteRoute( ? )}");
			statement.setInt(1, routeId);
			statement.execute();
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
	}

	@Override
	public IRoute getRouteByStation(int sourcePoint, int destinationPoint) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		IRoute route = setupAbstractFactory.createNewRoute();

		try {
			statement = connection.prepareCall("{call getRoutebyStation(?, ?)}");
			statement.setInt(1, sourcePoint);
			statement.setInt(2, destinationPoint);

			boolean hasRoute = statement.execute();
			if (hasRoute) {
				resultSet = statement.getResultSet();
				if (resultSet.next()) {

					route.setRouteId(resultSet.getInt("routeIdColumnName"));
					route.setDistance(resultSet.getDouble("distanceColumnName"));
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return route;
	}

}
