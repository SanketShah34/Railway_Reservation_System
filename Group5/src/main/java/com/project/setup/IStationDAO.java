package com.project.setup;

import java.util.List;

public interface IStationDAO {
	
	public void save(IStation station);
	
	public List<Station> getAllStation();
	
	public IStation getStation(Integer sId);
	
	public void delete(Integer sId);
}
