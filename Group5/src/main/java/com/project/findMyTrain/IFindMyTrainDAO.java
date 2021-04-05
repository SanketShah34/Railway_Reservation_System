package com.project.findMyTrain;

import java.util.Date;

public interface IFindMyTrainDAO {
	public void getLiveTrainStatus(int trainCode, Date startDate);
	
	public double getRouteInfo(Integer startStation, Integer endStation);

}
