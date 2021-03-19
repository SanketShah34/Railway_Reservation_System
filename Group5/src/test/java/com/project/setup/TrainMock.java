package com.project.setup;

public class TrainMock {

	public ITrain createTrainMock(ITrain train) {
		train.setTrainId(1);
		train.setTrainCode(1);
		train.setTrainName("Halifax Express");
		train.setTrainType("AC Seater");
		train.setDays("Mon");
		train.setDepartureTime("12:30");
		train.setTotalCoaches(7);
		train.setStartStation("A1");
		train.setMiddleStations("A2, A3");
		train.setEndStation("A4");
		train.setTotalStation(null);
		train.setFare(11.0);
		train.setPickUPTime("11:20");
		train.setDropTime("05:10");
		train.setAvailableSeat(50);
		train.setTotalDistance(25);
		return train;
	}
}
