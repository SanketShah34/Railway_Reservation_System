package com.project.setup;

public abstract class RouteErrorCodes {
	public static final String sourceStationMissing = "Source station is mandatory. ";
	public static final String destinationStationMissing = "Destination station is mandatory. ";
	public static final String sourceAndDestinationStationSame = "Source station and Destination station cannot be same. ";
	public static final String distanceNegativeOrZero = "Distance cannot be less then 0. ";
}
