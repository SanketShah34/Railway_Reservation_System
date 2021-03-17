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
@ComponentScan("com.code.service")
public class StationDAO implements IStationDAO {

	List<Station> listOfStation = new ArrayList();

	@Override
	public void save(IStation station) {

		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();
		CallableStatement stmt = null;
		if (station.getSId() == 0) {
			System.out.println("in add new ");
			try {
				stmt = conn.prepareCall("{call addStation( ? , ? , ? , ?)}");
				stmt.setString(1, station.getStationName());
				stmt.setString(2, station.getStationCode());
				stmt.setString(3, station.getStationCity());
				stmt.setString(4, station.getStationState());
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				databaseUtilities.closeConnection(conn);
			}
		} else {
			System.out.println("in edit");
			try {
				stmt = conn.prepareCall("{call editStation( ? , ? , ? , ? , ?)}");
				stmt.setInt(1, station.getSId());
				stmt.setString(2, station.getStationName());
				stmt.setString(3, station.getStationCode());
				stmt.setString(4, station.getStationCity());
				stmt.setString(5, station.getStationState());
				stmt.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					stmt.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
				databaseUtilities.closeConnection(conn);
			}
		}
	}

	@Override
	public List<Station> getAllStation() {

		listOfStation.removeAll(listOfStation);
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();
		CallableStatement stmt = null;
		ResultSet resultSet = null;

		try {
			stmt = conn.prepareCall("{call getAllStation()}");
			boolean hadResultsForList = stmt.execute();
			if (hadResultsForList) {
				resultSet = stmt.getResultSet();
				while (resultSet.next()) {
					Station station = new Station();
					station.setSId(resultSet.getInt("sId"));
					station.setStationName(resultSet.getString("stationName"));
					station.setStationCode(resultSet.getString("stationCode"));
					station.setStationCity(resultSet.getString("stationCity"));
					station.setStationState(resultSet.getString("stationState"));
					listOfStation.add(station);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				resultSet.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			databaseUtilities.closeConnection(conn);
		}

		return listOfStation;
	}

	@Override
	public IStation getStation(Integer sId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();
		CallableStatement stmt = null;
		ResultSet resultSet = null;

		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStation station = setupAbstractFactory.createStation();

		try {
			stmt = conn.prepareCall("{call getStation(?)}");
			stmt.setInt(1, sId);
			boolean hadStation = stmt.execute();
			if (hadStation) {
				resultSet = stmt.getResultSet();
				if (resultSet.next()) {
					station.setSId(resultSet.getInt("sId"));
					station.setStationName(resultSet.getString("stationName"));
					station.setStationCode(resultSet.getString("stationCode"));
					station.setStationCity(resultSet.getString("stationCity"));
					station.setStationState(resultSet.getString("stationState"));
				}
			}
		} catch (SQLException e) {

			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			databaseUtilities.closeConnection(conn);
		}
		return station;
	}

	@Override
	public void delete(Integer sId) {
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection conn = databaseUtilities.establishConnection();
		CallableStatement stmt = null;
		try {
			stmt = conn.prepareCall("{call deleteStation( ? )}");
			stmt.setInt(1, sId);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			databaseUtilities.closeConnection(conn);
		}
	}

}
