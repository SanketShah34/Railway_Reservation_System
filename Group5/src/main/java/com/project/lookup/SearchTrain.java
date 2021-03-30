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

	@Override
	public String getSourceStation() {
		return sourceStation;
	}

	@Override
	public void setSourceStation(String sourceStation) {
		this.sourceStation = sourceStation;
	}

	@Override
	public String getDestinationStation() {
		return destinationStation;
	}

	@Override
	public void setDestinationStation(String destinationStation) {
		this.destinationStation = destinationStation;
	}

	@Override
	public Date getDateofJourny() {
		return dateofJourny;
	}

	@Override
	public void setDateofJourny(Date dateofJourny) {
		this.dateofJourny = dateofJourny;
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
	public boolean issourceStationAndDestinationStationSame(String sourceStation, String destinationStation) {
		boolean valid = false;
		if (sourceStation.equals(destinationStation)) {
			valid = true;
		}
		return valid;
	}

	@Override
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
