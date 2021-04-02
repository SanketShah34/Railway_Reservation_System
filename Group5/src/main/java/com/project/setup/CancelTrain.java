package com.project.setup;

import java.sql.Date;
import java.time.format.DateTimeFormatter;

public class CancelTrain implements ICancelTrain{

	private int trainCode;
	private Date cancellationDate;
	
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
	public Date getCancellationDate() {
		return cancellationDate;
	}

	@Override
	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}
	
	@Override
	public String localDateToString() {
//		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-dd");
//		return cancellationDate.f
		return "";
	}
	
	@Override
	public String validateTrainDate() {
		return "";
	}
	
}
