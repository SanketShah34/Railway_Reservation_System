package com.project.lookup;

import java.sql.Date;

public class SearchTrain implements ISearchTrain {

	private String sourceStation;
	private String destinationStation;
	private Date dateofJourny;
	public String trainType;

	public SearchTrain() {
	}

	public SearchTrain(String sourceStation, String destinationStation, Date dateofJourny, String trainType) {
		super();
		this.sourceStation = sourceStation;
		this.destinationStation = destinationStation;
		this.dateofJourny = dateofJourny;
		this.trainType = trainType;
	}

	public String getSourceStation() {
		return sourceStation;
	}

	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	public String getDestinationStation() {
		return destinationStation;
	}

	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	public Date getDateofJourny() {
		return dateofJourny;
	}

	public void setDateofJourny(Date dateofJourny) {
		this.dateofJourny = dateofJourny;
	}

	public String getTrainType() {
		return trainType;
	}

	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	public boolean issourceStationAndDestinationStationSame(String sourceStation, String destinationStation) {
		boolean valid = false;
		if (sourceStation.equals(destinationStation)) {
			valid = true;
		}
		return valid;
	}

	public boolean isDatePreviousDate(Date date) {
		Date a = new Date(System.currentTimeMillis());
		Date b = (Date) date;
		if (a.compareTo(b) == 1) {
			return true;
		} else {
			return false;
		}
	}

}
