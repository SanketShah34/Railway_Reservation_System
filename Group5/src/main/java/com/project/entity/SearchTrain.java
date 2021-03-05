package com.project.entity;

import java.sql.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

public class SearchTrain {
	
	@NotNull(message = "Source Station cannot be null!!")
	@NotEmpty(message = "Source Station cannot be null!!")
	private String sourceStation;
	
	@NotNull(message = "Destination Station  cannot be null!!")
	@NotEmpty(message = "Destination Station cannot be null!!")
	private String destinationStation;
	
	@NotNull(message = "Date of birth cannot be null")
	private Date dateofJourny;
	
	@NotNull(message = "Train Type cannot be null!!")
	@NotEmpty(message = "Train Type cannot be null!!")
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
	
	
}
