package com.project.setup;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CancelTrain implements ICancelTrain{

	private int trainCode;
	private LocalDate cancellationDate;
	
	public CancelTrain() {}

	@Override
	public int getTrainCode() {
		return trainCode;
	}

	@Override
	public void setTrainCode(int trainCode) {
		this.trainCode = trainCode;
	}

	@Override
	public LocalDate getCancellationDate() {
		return cancellationDate;
	}

	@Override
	public void setCancellationDate(LocalDate cancellationDate) {
		this.cancellationDate = cancellationDate;
	}
	
	@Override
	public String localDateToString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
		return cancellationDate.format(formatter);
	}
	@Override
	public String validateTrainDate() {
		return "";
	}
	
}
