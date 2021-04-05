package com.project.lookup;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;
import com.project.setup.TrainMock;

public class SearchTrainDAOMock implements ISearchTrainDAO {

	@Override
	public List<ITrain> searchTrains(ISearchTrain searchTrain) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
		TrainMock trainMock = setupAbstractFactoryTest.createTrainMock();
		ITrain train = setupAbstractFactory.createNewTrain();
		train = trainMock.createTrainMock(train);
		
		List<ITrain> trainList = new ArrayList<ITrain>(0);
		trainList.add(train);
		return trainList;	
	}

}
