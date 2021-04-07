package com.project.findMyTrain;

public interface IDistanceData {
	public int getStartStation();
	
	public void setStartStation(int startStation);

	public int getEndStation();

	public void setEndStation(int endStation);

	public double getDistance();

	public void setDistance(double distance);

	public boolean isDateValid(String string);

	public boolean isTrainCodeValid(String trainCode);
}
