package com.project.setup;


import javax.validation.constraints.NotNull;

public class Station implements IStation {
	
	public int sId;
	
	@NotNull(message = "stationName can not be null!!")

	public String stationName;
	@NotNull(message = "Station Code can not be null!!")

	public String stationCode;
	@NotNull(message = "station City can not be null!!")
	
	public String stationCity;
	@NotNull(message = "Station State can not be null!!")

	public String stationState;
	
	public Station(){
		
	}
	
	public Station(int sId, String stationName, String stationCode, String stationCity, String stationState) {
		super();
		this.sId = sId;
		this.stationName = stationName;
		this.stationCode = stationCode;
		this.stationCity = stationCity;
		this.stationState = stationState;
	}
	
	@Override
	public int getSId() {
		return sId;
	}

	@Override
	public void setSId(int sId) {
		this.sId = sId;
	}

	@Override
	public String getStationName() {
		return stationName;
	}
	@Override
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	@Override
	public String getStationCode() {
		return stationCode;
	}
	@Override
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	
	@Override
	public String getStationState() {
		return stationState;
	}
	@Override
	public void setStationState(String stationState) {
		this.stationState = stationState;
	}
	@Override
	public String getStationCity() {
		return stationCity;
	}
	@Override
	public void setStationCity(String stationCity) {
		this.stationCity = stationCity;
	}
	
	
}
