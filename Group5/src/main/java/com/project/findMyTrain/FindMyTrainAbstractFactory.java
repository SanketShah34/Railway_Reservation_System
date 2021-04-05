package com.project.findMyTrain;


public abstract class FindMyTrainAbstractFactory {
	private static FindMyTrainAbstractFactory instance = null;
	
	public static FindMyTrainAbstractFactory instance() {
		if (null == instance) {
			instance = new FindMyTrainConcreteFactory();
		}
		return instance;
	}

}
