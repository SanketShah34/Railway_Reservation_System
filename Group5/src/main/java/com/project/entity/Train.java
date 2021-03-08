package com.project.entity;


import java.util.List;

import javax.validation.constraints.NotNull;

public class Train {

	public int trainId;
	
	@NotNull(message = "Train Code can not be null!!")
	public int trainCode;
	
	@NotNull(message = "Train Name can not be null!!")
	public String trainName;
	
	@NotNull(message = "Train Type can not be null!!")
	public String trainType;
	
	@NotNull(message = "Days can not be null!!")
	public String days;
	
	@NotNull(message = "Departure Time can not be null!!")
	public String departureTime;
	
	public int totalCoaches;
	@NotNull(message = "Start Station can not be null!!")
	public String startStation;
	
	public String middleStations;
	
	@NotNull(message = "Start Station can not be null!!")
	public String endStation;

	public List<Integer> totalStation;
	
	public int fair;
	
	public String pickUPTime;
	
	public String dropTime;
	
	public int totalDistance;
	
	public Train() {
		
	}
	
	public Train(int trainId,
			@NotNull(message = "Train Code can not be null!!") int trainCode,
			@NotNull(message = "Train Name can not be null!!") String trainName,
			@NotNull(message = "Train Type can not be null!!") String trainType,
			@NotNull(message = "Days can not be null!!") String days,
			@NotNull(message = "Departure Time can not be null!!") String departureTime,
			int totalCoaches,
			@NotNull(message = "Start Station can not be null!!") String startStation,
			@NotNull(message = "Middle Stations can not be null!!") String middleStations,
			@NotNull(message = "Start Station can not be null!!") String endStation) {
		super();
		this.trainId = trainId;
		this.trainCode = trainCode;
		this.trainName = trainName;
		this.trainType = trainType;
		this.days = days;
		this.departureTime = departureTime;
		this.totalCoaches = totalCoaches;
		this.startStation = startStation;
		this.middleStations = middleStations;
		this.endStation = endStation;
	}

	public int getTrainId() {
		return trainId;
	}

	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	public int getTrainCode() {
		return trainCode;
	}

	public void setTrainCode(int trainCode) {
		this.trainCode = trainCode;
	}

	public String getTrainName() {
		return trainName;
	}

	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public String getDays() {
		return days;
	}

	public void setDays(String days) {
		this.days = days;
	}

	public String getDepartureTime() {
		return departureTime;
	}

	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	public int getTotalCoaches() {
		return totalCoaches;
	}

	public void setTotalCoaches(int totalCoaches) {
		this.totalCoaches = totalCoaches;
	}

	public String getStartStation() {
		return startStation;
	}

	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	public String getMiddleStations() {
		return middleStations;
	}

	public void setMiddleStations(String middleStations) {
		this.middleStations = middleStations;
	}

	public String getEndStation() {
		return endStation;
	}

	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	

	public List<Integer> getTotalStation() {
		return totalStation;
	}

	public void setTotalStation(List<Integer> totalStation) {
		this.totalStation = totalStation;
	}

	

	public int getFair() {
		return fair;
	}

	public void setFair(int fair) {
		this.fair = fair;
	}

	public String getPickUPTime() {
		return pickUPTime;
	}

	public void setPickUPTime(String pickUPTime) {
		this.pickUPTime = pickUPTime;
	}

	public String getDropTime() {
		return dropTime;
	}

	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}
	
	public int getTotalDistance() {
		return this.totalDistance;
	}
	
	public void setTotalDistance(int distance) {
		this.totalDistance = distance;
	}
}
