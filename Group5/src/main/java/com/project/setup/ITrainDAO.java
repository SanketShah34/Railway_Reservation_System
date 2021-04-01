package com.project.setup;

import java.util.List;

public interface ITrainDAO {

	public List<Train> getAllTrain();

	public boolean saveTrain(ITrain train);

	public ITrain getTrain(Integer trainId);

	public void deleteTrain(Integer trainId);

}
