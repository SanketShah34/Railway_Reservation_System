package com.project.findMyTrain;

public class DistanceData implements IDistanceData {
		private int startStation;
		private int endStation;
		private double distance;
	    
	 	public DistanceData() {
	    }
	    
	    public DistanceData(int startStation, int endStation, double distance) {
	        super();
	        this.startStation = startStation;
	        this.endStation = endStation;
	        this.distance = distance;
	    }

	    public int getStartStation() {
	        return startStation;
	    }

	    public void setStartStation(int startStation) {
	        this.startStation = startStation;
	    }

	    public int getEndStation() {
	        return endStation;
	    }

	    public void setEndStation(int endStation) {
	        this.endStation = endStation;
	    }

	    public double getDistance() {
	        return distance;
	    }

	    public void setDistance(double distance) {
	        this.distance = distance;
	    }
	    
	    public boolean isStringNullOrEmpty(String string) {
			if (null == string) {
				return true;
			}
			return string.isEmpty();
		}
	    
	    public boolean isTrainCodeValid(String trainCode) {
			return isStringNullOrEmpty(trainCode);
		}

		public boolean isDateValid(String date) {
			return isStringNullOrEmpty(date);
		}
}
