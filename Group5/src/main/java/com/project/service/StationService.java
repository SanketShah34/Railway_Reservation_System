package com.project.service;

import java.util.List;

import com.project.entity.Station;

public interface StationService {
	public void save(Station station);
	
	public List<Station> ListOfStations();
	
	public Station getStation(Integer sid);
	
	public void deleteStation(Integer sid);
}
