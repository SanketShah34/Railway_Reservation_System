package com.project.dao.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.project.dao.StationDAO;
import com.project.entity.Station;
import com.project.service.DButilities;

@Component
@ComponentScan("com.code.service")
public class StationDAOImpl implements StationDAO {

	
	List<Station> listOfStation = new ArrayList<>();
	

	@Autowired
	DButilities dbutilities;

	@Override
	public void save(Station station) {
		Connection conn = dbutilities.EstConnection();
		if(station.getSid() == 0) {
			System.out.println("in add new ");
			try {
				CallableStatement stmt = conn.prepareCall("{call addStation( ? , ? , ? , ?)}");
				stmt.setString(1, station.getStationName());
				stmt.setString(2, station.getStationCode());
				stmt.setString(3, station.getStationCity());
				stmt.setString(4, station.getStationState());

				 stmt.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		else {
			System.out.println("in edit");
			try {
				CallableStatement stmt = conn.prepareCall("{call editStation( ? , ? , ? , ? , ?)}");
				stmt.setInt(1, station.getSid());
				stmt.setString(2, station.getStationName());
				stmt.setString(3, station.getStationCode());
				stmt.setString(4, station.getStationCity());
				stmt.setString(5, station.getStationState());

				 stmt.execute();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		

	}

	@Override
	public List<Station> getAllStation() {
		listOfStation.removeAll(listOfStation);
		Connection conn = dbutilities.EstConnection();

		try {
			CallableStatement stmt = conn.prepareCall("{call getAllStation()}");

			boolean hadResultsForList = stmt.execute();

			if (hadResultsForList) {
				
				ResultSet resultSet = stmt.getResultSet();
				while (resultSet.next()) {
					Station station = new Station();
					station.setSid(resultSet.getInt("sid"));
			//		System.out.println("++" + station.getId());

					station.setStationName(resultSet.getString("stationName"));
	//		System.out.println("++"+station.getStationName());
					station.setStationCode(resultSet.getString("stationCode"));
			//		System.out.println("++"+station.getStationCode());
					station.setStationCity(resultSet.getString("stationCity"));
			//		System.out.println("++"+station.getStationCity());
					station.setStationState(resultSet.getString("stationState"));
			//		System.out.println("++"+station.getStationState());
				 
					listOfStation.add(station);
				
				}
			}

	

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return listOfStation;
	}

	@Override
	public Station getStation(Integer sid) {
		Connection conn = dbutilities.EstConnection();
		
		Station station = new Station();
		try {
			CallableStatement stmt = conn.prepareCall("{call getStation(?)}");
			stmt.setInt(1, sid);
			
			boolean hadStation = stmt.execute();
			if (hadStation) {
				ResultSet resultSet = stmt.getResultSet();
				if (resultSet.next()) {
					station.setSid(resultSet.getInt("sid"));
					station.setStationName(resultSet.getString("stationName"));
					station.setStationCode(resultSet.getString("stationCode"));
					station.setStationCity(resultSet.getString("stationCity"));
					station.setStationState(resultSet.getString("stationState"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		// TODO Auto-generated method stub
		return station;
		}

	@Override
	public void delete(Integer sid) {
		
		Connection conn = dbutilities.EstConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call deleteStation( ? )}");
			stmt.setInt(1, sid);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// TODO Auto-generated method stub
		
	}
	
}
