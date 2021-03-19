package com.project.lookup;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;

public class SearchTrain implements ISearchTrain{
	
	private String sourceStation;
	private String destinationStation;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
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
	
	public boolean issourceStationAndDestinationStationSame(String sourceStation , String destinationStation) {
		boolean valid = false;
		if(sourceStation.equals(destinationStation)) {
			valid = true;
		}
		return valid;
	}
	
	
}
