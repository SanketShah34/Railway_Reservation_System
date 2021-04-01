package com.project.setup;

import java.time.LocalDate;

public interface ICancelTrain {

	int getTrainCode();

	void setTrainCode(int trainCode);

	LocalDate getCancellationDate();

	void setCancellationDate(LocalDate cancellationDate);

	String validateTrainDate();

	String localDateToString();

}