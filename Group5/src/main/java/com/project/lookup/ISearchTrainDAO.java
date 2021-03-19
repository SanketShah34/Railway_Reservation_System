package com.project.lookup;

import java.util.List;
import com.project.setup.ITrain;

public interface ISearchTrainDAO {

	public List<ITrain> searchTrains(ISearchTrain searchTrain);
	
}
