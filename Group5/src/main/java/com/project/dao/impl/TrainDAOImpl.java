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

import com.project.dao.TrainDAO;
import com.project.entity.Train;
import com.project.service.DButilities;

@Component
@ComponentScan("com.code.service")
public class TrainDAOImpl implements TrainDAO {

	@Autowired
	DButilities dbutilities;
	
	@Override
	public List<Train> getAllTrain() {
		List<Train> listOfTrain = new ArrayList<Train>();
		listOfTrain.removeAll(listOfTrain);
		Connection conn = dbutilities.EstConnection();

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
					listOfTrain.add(train);
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return listOfTrain;
	}

	@Override
	public void saveTrain(Train train) {
		Connection conn = dbutilities.EstConnection();
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
				stmt.setInt(7, train.getStartStation());
				stmt.setString(8, train.getMiddleStations());
				stmt.setInt(9, train.getEndStation());

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
				stmt.setInt(8, train.getStartStation());
				stmt.setString(9, train.getMiddleStations());
				stmt.setInt(10, train.getEndStation());

				stmt.execute();

			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public Train getTrain(Integer trainId) {
		Connection conn = dbutilities.EstConnection();
		
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
					train.setStartStation(resultSet.getInt(8));
					train.setMiddleStations(resultSet.getString(9));
					train.setEndStation(resultSet.getInt(10));
				}
			}
		} 
		catch (SQLException e) {
			e.printStackTrace();
		}
		return train;
	}

	@Override
	public void deleteTrain(Integer trainId) {
		Connection conn = dbutilities.EstConnection();
		try {
			CallableStatement stmt = conn.prepareCall("{call deleteTrain( ? )}");
			stmt.setInt(1, trainId);
			stmt.execute();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
