package com.project.entity;


import javax.validation.constraints.NotNull;

public class Station {
	
	public int sid;
	
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
	
	public Station(int sid, String stationName, String stationCode, String stationCity, String stationState) {
		super();
		this.sid = sid;
		this.stationName = stationName;
		this.stationCode = stationCode;
		this.stationCity = stationCity;
		this.stationState = stationState;
	}
	
	public int getSid() {
		return sid;
	}

	public void setSid(int sid) {
		this.sid = sid;
	}

	public String getStationName() {
		return stationName;
	}
	public void setStationName(String stationName) {
		this.stationName = stationName;
	}
	public String getStationCode() {
		return stationCode;
	}
	public void setStationCode(String stationCode) {
		this.stationCode = stationCode;
	}
	
	public String getStationState() {
		return stationState;
	}
	public void setStationState(String stationState) {
		this.stationState = stationState;
	}
	public String getStationCity() {
		return stationCity;
	}
	public void setStationCity(String stationCity) {
		this.stationCity = stationCity;
	}
	
	
}
