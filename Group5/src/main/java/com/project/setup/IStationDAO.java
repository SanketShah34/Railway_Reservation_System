package com.project.setup;

import java.util.List;

public interface IStationDAO {
	
	public boolean save(IStation station);
	
	public List<IStation> getAllStation();
	
	public IStation getStation(Integer sId);
	
	public void delete(Integer sId);
	
	public boolean isStationUnique(String stationName,String stationCode , int SId);
}
