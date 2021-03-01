package com.project.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.StationDAO;
import com.project.entity.Station;

@Service
public class StationServiceImpl implements StationService {

	@Autowired
	StationDAO stationDAO;
	
	@Override
	public void save(Station station) {
		// TODO Auto-generated method stub
		 stationDAO.save(station);
	}

	@Override
	public List<Station> listOfStations() {
		// TODO Auto-generated method stub
		return stationDAO.getAllStation();
	}

	@Override
	public Station getStation(Integer sId) {
		// TODO Auto-generated method stub
		return stationDAO.getStation(sId);
	}

	@Override
	public void deleteStation(Integer sId) {
		// TODO Auto-generated method stub
		 stationDAO.delete(sId);
	}

}
