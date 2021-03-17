package com.project.setup;

public class Route implements IRoute {
	public int rId;
	public int sourceId;
	public int destinationId;
	public IStation source;
	public IStation destination;
	public double distance;
		
	public Route() {}
	
	public Route(int rId, IStation source, int sourceId, IStation destination, int destinationId, double distance) {
		this.rId = rId;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.sourceId = sourceId;
		this.destinationId = destinationId;
	}

	@Override
	public IStation getSource() {
		return source;
	}

	@Override
	public void setSource(IStation source) {
		this.source = source;
	}

	@Override
	public IStation getDestination() {
		return destination;
	}

	@Override
	public void setDestination(IStation destintion) {
		this.destination = destintion;
	}
	
	@Override
	public double getDistance() {
		return distance;
	}

	@Override
	public void setDistance(double distance) {
		this.distance = distance;
	}

	@Override
	public int getRId() {
		return rId;
	}

	@Override
	public void setRId(int rId) {
		this.rId = rId;
	}

	@Override
	public int getSourceId() {
		return sourceId;
	}

	@Override
	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	@Override
	public int getDestinationId() {
		return destinationId;
	}

	@Override
	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
}
