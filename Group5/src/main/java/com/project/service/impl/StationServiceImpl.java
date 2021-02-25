package com.project.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.dao.StationDAO;
import com.project.entity.Station;
import com.project.service.StationService;

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
	public List<Station> ListOfStations() {
		// TODO Auto-generated method stub
		return stationDAO.getAllStation();
	}

	@Override
	public Station getStation(Integer sid) {
		// TODO Auto-generated method stub
		return stationDAO.getStation(sid);
	}

	@Override
	public void deleteStation(Integer sid) {
		// TODO Auto-generated method stub
		 stationDAO.delete(sid);
	}

}
