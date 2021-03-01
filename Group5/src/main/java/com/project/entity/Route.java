package com.project.entity;

import javax.validation.constraints.NotNull;

public class Route {
	public int rId;
	public int sourceId;
	public int destinationId;
	public Station source;
	public Station destination;

	@NotNull(message = "Distance can not be empty!!")
	public double distance;
	
	public Route(){
		
	}
	
	public Route(int rId, Station source, int sourceId, Station destination, int destinationId, double distance) {
		this.rId = rId;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
		this.sourceId = sourceId;
		this.destinationId = destinationId;
	}

	public Station getSource() {
		return source;
	}

	public void setSource(Station source) {
		this.source = source;
	}

	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destintion) {
		this.destination = destintion;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}

	public int getRId() {
		return rId;
	}

	public void setRId(int rId) {
		this.rId = rId;
	}

	public int getSourceId() {
		return sourceId;
	}

	public void setSourceId(int sourceId) {
		this.sourceId = sourceId;
	}

	public int getDestinationId() {
		return destinationId;
	}

	public void setDestinationId(int destinationId) {
		this.destinationId = destinationId;
	}
}
