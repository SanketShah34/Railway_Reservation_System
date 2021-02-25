package com.project.entity;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class Train {

	public int trainId;
	
	@NotNull(message = "Train Code can not be null!!")
	@NotEmpty(message = "Train Code can not be empty!!")
	public int trainCode;
	
	@NotNull(message = "Train Name can not be null!!")
	@NotEmpty(message = "Train Name can not be empty!!")
	public String trainName;
	
	@NotNull(message = "Train Type can not be null!!")
	@NotEmpty(message = "Train Type can not be empty!!")
	public String trainType;
	
	@NotNull(message = "Days can not be null!!")
	@NotEmpty(message = "Days can not be empty!!")
	public String days;
	
	@NotNull(message = "Departure Time can not be null!!")
	@NotEmpty(message = "Departure Time can not be empty!!")
	public String departureTime;
	
	public int totalCoaches;
	
	@NotNull(message = "Start Station can not be null!!")
	@NotEmpty(message = "Start Station can not be empty!!")
	public int startStation;
	
	@NotNull(message = "Middle Stations can not be null!!")
	@NotEmpty(message = "Middle Stations can not be empty!!")
	public String middleStations;
	
	@NotNull(message = "Start Station can not be null!!")
	@NotEmpty(message = "End Station can not be empty!!")
	public int endStation;

	public Train() {
		
	}
	
	public Train(int trainId,
			@NotNull(message = "Train Code can not be null!!") @NotEmpty(message = "Train Code can not be empty!!") int trainCode,
			@NotNull(message = "Train Name can not be null!!") @NotEmpty(message = "Train Name can not be empty!!") String trainName,
			@NotNull(message = "Train Type can not be null!!") @NotEmpty(message = "Train Type can not be empty!!") String trainType,
			@NotNull(message = "Days can not be null!!") @NotEmpty(message = "Days can not be empty!!") String days,
			@NotNull(message = "Departure Time can not be null!!") @NotEmpty(message = "Departure Time can not be empty!!") String departureTime,
			int totalCoaches,
			@NotNull(message = "Start Station can not be null!!") @NotEmpty(message = "Start Station can not be empty!!") int startStation,
			@NotNull(message = "Middle Stations can not be null!!") @NotEmpty(message = "Middle Stations can not be empty!!") String middleStations,
			@NotNull(message = "Start Station can not be null!!") @NotEmpty(message = "End Station can not be empty!!") int endStation) {
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

	public int getStartStation() {
		return startStation;
	}

	public void setStartStation(int startStation) {
		this.startStation = startStation;
	}

	public String getMiddleStations() {
		return middleStations;
	}

	public void setMiddleStations(String middleStations) {
		this.middleStations = middleStations;
	}

	public int getEndStation() {
		return endStation;
	}

	public void setEndStation(int endStation) {
		this.endStation = endStation;
	}
}
