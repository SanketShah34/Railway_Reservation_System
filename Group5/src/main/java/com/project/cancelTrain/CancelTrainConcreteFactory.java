package com.project.cancelTrain;

public class CancelTrainConcreteFactory extends CancelTrainAbstractFactory{
	private ITrainCancellationDAO trainCancellationDAO;
	
	@Override
	public ITrainCancellationDAO createTrainCancellationDAO() {
		if (trainCancellationDAO == null ) {
			trainCancellationDAO = new TrainCancellationDAO();
		}
		return trainCancellationDAO;
	}
	
	@Override
	public ITrainCancellationDAO createNewTrainCancellationDAO() {
		return new TrainCancellationDAO();
	}
	
}
