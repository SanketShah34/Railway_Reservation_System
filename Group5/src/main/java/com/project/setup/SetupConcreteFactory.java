package com.project.setup;

public class SetupConcreteFactory extends SetupAbstractFactory{
	
	private IRouteDAO routeDAO;
    private IStationDAO stationDAO;
    private ITrainDAO trainDAO;
    private IRoute route;
    private IStation station;
    private ITrain train;
    
    public IRouteDAO createRouteDAO() {
    	if (routeDAO == null) {
    		routeDAO = new RouteDAO();
    	}
    	return routeDAO;
    }
    
    public IRouteDAO createNewRouteDAO() {
    	return new RouteDAO();
    }
    
    public IRoute createRoute() {
    	if (route == null) {
    		route = new Route();
    	}
    	return route;
    }
    
    public IRoute createNewRoute() {
    	return new Route();
    }
    
    public IStationDAO createStationDAO() {
    	if (stationDAO == null) {
    		stationDAO = new StationDAO();
    	}
    	return stationDAO;
    }
    
    public IStationDAO createNewStationDAO() {
    	return new StationDAO();
    }
    
    public IStation createStation() {
    	if (station == null) {
    		station = new Station();
    	}
    	return station;
    }
    
    public IStation createNewStation() {
    	return new Station();
    }
    
    public ITrainDAO createTrainDAO() {
    	if (trainDAO == null) {
    		trainDAO = new TrainDAO();
    	}
    	return trainDAO;
    }
    
    public ITrainDAO createNewTrainDAO() {
    	return new TrainDAO();
    }
    
    public ITrain createTrain() {
    	if (train == null) {
    		train = new Train();
    	}
    	return train;
    }
    
    public ITrain createNewTrain() {
    	return new Train();
    }
}
