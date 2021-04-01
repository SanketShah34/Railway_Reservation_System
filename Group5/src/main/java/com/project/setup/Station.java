package com.project.setup;

public class Station implements IStation {
	public int stationId;
	public String stationName;
	public String stationCode;
	public String stationCity;
	public String stationState;

	public Station() {

	}

	public Station(int stationId, String stationName, String stationCode, String stationCity, String stationState) {
		super();
		this.stationId = stationId;
		this.stationName = stationName;
		this.stationCode = stationCode;
		this.stationCity = stationCity;
		this.stationState = stationState;
	}

	public int getStationId() {
		return stationId;
	}

	public void setStationId(int stationId) {
		this.stationId = stationId;
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

	public boolean isStationNameInvalid() {
		String StationNameWithoutSpace = this.stationName.trim();
		if (StationNameWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	public boolean isStationCodeInvalid() {
		String StationCodeWithoutSpace = this.stationCode.trim();
		if (StationCodeWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	public boolean isStationStateInvalid() {
		String StationStateWithoutSpace = this.stationState.trim();
		if (StationStateWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	public boolean isStationCityInvalid() {
		String StationCityWithoutSpace = this.stationCity.trim();
		if (StationCityWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

}
