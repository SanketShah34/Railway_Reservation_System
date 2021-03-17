package com.project.setup;

public abstract class SetupAbstractFactory {
	 private static SetupAbstractFactory instance = null;
	 
	 public abstract IRouteDAO createRouteDAO();
	 public abstract IRouteDAO createNewRouteDAO();
	 public abstract IRoute createRoute();
	 public abstract IRoute createNewRoute();
	 public abstract IStationDAO createStationDAO();
	 public abstract IStationDAO createNewStationDAO();
	 public abstract IStation createStation();
	 public abstract IStation createNewStation();
	 public abstract ITrainDAO createTrainDAO();
	 public abstract ITrainDAO createNewTrainDAO();
	 public abstract ITrain createTrain();
	 public abstract ITrain createNewTrain();
	 
	 public static SetupAbstractFactory instance() {
        if (instance == null) {
            instance = new SetupConcreteFactory();
        }
        return instance;
	}
}
