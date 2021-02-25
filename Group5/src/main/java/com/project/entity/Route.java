package com.project.entity;

import javax.validation.constraints.NotNull;

public class Route {
	public int rId;
	
	public Station source;
	public Station destination;
	
	@NotNull(message = "Distance can not be empty!!")
	public double distance;
	
	public Route(){
		
	}
	
	public Route(int rId, Station source, Station destination, double distance) {
		super();
		this.rId = rId;
		this.source = source;
		this.destination = destination;
		this.distance = distance;
	}

	public int getRId() {
		return rId;
	}

	public void setRId(int rId) {
		this.rId = rId;
	}

	public Station getSource() {
		return source;
	}

	public void setSource(Station source) {
		this.source = source;
	}

	public int getSourceStationId() {
		return this.source.sid;
	}
	
	public Station getDestination() {
		return destination;
	}

	public void setDestination(Station destintion) {
		this.destination = destintion;
	}

	public int getDestinationStationId() {
		return this.destination.sid;
	}
	
	public double getDistance() {
		return distance;
	}

	public void setDistance(double distance) {
		this.distance = distance;
	}
}
