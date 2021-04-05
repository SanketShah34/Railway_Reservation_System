package com.project.findMyTrain;

import java.util.Date;
import java.util.Map;

import com.project.setup.ITrain;

public interface IFindMyTrainDAO {
	public ITrain getLiveTrainStatus(int trainCode, Date startDate);
	
	public double getRouteInformation(Integer startStation, Integer endStation);
	
	 public Map<Integer, String> getStationInformation();

}
