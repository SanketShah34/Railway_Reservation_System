package com.project.setup;


import java.util.List;

import javax.validation.constraints.NotNull;

public class Train implements ITrain {

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
	
	public double fare;
	
	public String pickUPTime;
	
	public String dropTime;
	
	public int availableSeat;
	
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

	@Override
	public int getTrainId() {
		return trainId;
	}

	@Override
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	@Override
	public int getTrainCode() {
		return trainCode;
	}

	@Override
	public void setTrainCode(int trainCode) {
		this.trainCode = trainCode;
	}

	@Override
	public String getTrainName() {
		return trainName;
	}

	@Override
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	@Override
	public String getTrainType() {
		return trainType;
	}

	@Override
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	@Override
	public String getDays() {
		return days;
	}

	@Override
	public void setDays(String days) {
		this.days = days;
	}

	@Override
	public String getDepartureTime() {
		return departureTime;
	}

	@Override
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public int getTotalCoaches() {
		return totalCoaches;
	}

	@Override
	public void setTotalCoaches(int totalCoaches) {
		this.totalCoaches = totalCoaches;
	}

	@Override
	public String getStartStation() {
		return startStation;
	}

	@Override
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	@Override
	public String getMiddleStations() {
		return middleStations;
	}

	@Override
	public void setMiddleStations(String middleStations) {
		this.middleStations = middleStations;
	}

	@Override
	public String getEndStation() {
		return endStation;
	}

	@Override
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	

	@Override
	public List<Integer> getTotalStation() {
		return totalStation;
	}

	@Override
	public void setTotalStation(List<Integer> totalStation) {
		this.totalStation = totalStation;
	}

	

	@Override
	public double getFare() {
		return fare;
	}

	@Override
	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String getPickUPTime() {
		return pickUPTime;
	}

	@Override
	public void setPickUPTime(String pickUPTime) {
		this.pickUPTime = pickUPTime;
	}

	@Override
	public String getDropTime() {
		return dropTime;
	}

	@Override
	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}

	@Override
	public int getAvailableSeat() {
		return availableSeat;
	}

	@Override
	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	@Override
	public int getTotalDistance() {
		return totalDistance;
	}

	@Override
	public void setTotalDistance(int totalDistance) {
		this.totalDistance = totalDistance;
	}
	
	
	
	
}
