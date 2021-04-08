package com.project.findMyTrain;

import java.util.Date;
import com.project.setup.ITrain;

public interface IFindMyTrainLocation {
	public String findMyTrainCalculation(ITrain train, Date startDate);
}
