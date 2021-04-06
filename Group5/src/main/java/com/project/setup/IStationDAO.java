package com.project.setup;

import java.util.List;

public interface IStationDAO {

	public boolean save(IStation station);

	public List<IStation> getAllStation();

	public IStation getStation(Integer stationId);

	public void delete(Integer stationId);

	public boolean isStationUnique(String stationName, String stationCode, int stationId);
	
}
