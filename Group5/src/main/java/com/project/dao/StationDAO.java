package com.project.dao;

import java.util.List;

import com.project.entity.Station;

public interface StationDAO {
	public void save(Station station);
	
	public List<Station> getAllStation();
	
	public Station getStation(Integer sId);
	
	public void delete(Integer sId);
}
