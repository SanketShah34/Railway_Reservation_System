 package com.project.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.project.dao.IRouteDAO;
import com.project.dao.RouteDAO;
import com.project.entity.Route;
import com.project.entity.Train;
import com.project.service.IRouteService;

@Component
@ComponentScan("com.project.service")
public class findFair implements IfindFair {

	@Autowired
	RouteDAO routeDAO;

	public List<Train> findFairofTrainjourney(List<Train> trains, String sourceStation, String destinationStation) {

		for (int i = 0; i < trains.size(); i++) {

			System.out.println("-------------------------for train" + i + 1 + "--------------------");
			String time = trains.get(i).getDepartureTime();

			int timeInminutes = timeCounter(time);
			int timeRequiredByTrainToreachSourceStation = 0;
			int timeRequiredByTrainForDestinationStation = 0;
			int timerequiredByTrainToCoverOneKM = 1;

			int distance = 0;
			int distanceRequiredToreachSourceStation = 0;
			int distanceRequiredForDestinationStation = 0;
			
			int fair = 0;

			List<Integer> allStation = trains.get(i).getTotalStation();

			int sourceStationIndex = allStation.indexOf(Integer.parseInt(sourceStation));
			int destinationStationIndex = allStation.indexOf(Integer.parseInt(destinationStation));

			for (int k = 0; k < sourceStationIndex; k++) {
				Route route = routeDAO.getrouteByStation(allStation.get(k), allStation.get(k + 1));
				distanceRequiredToreachSourceStation += route.getDistance();
				timeRequiredByTrainToreachSourceStation += 10;

			}

			System.out.println("distance total train need to cover " + distanceRequiredToreachSourceStation);
			System.out.println(
					"timerequired by train to reach source station " + timeRequiredByTrainToreachSourceStation);
			System.out.println("time in minutes " + timeInminutes);
			System.out.println(
					"distance time  " + (distanceRequiredToreachSourceStation * timerequiredByTrainToCoverOneKM));

			timeRequiredByTrainToreachSourceStation = timeRequiredByTrainToreachSourceStation + timeInminutes
					+ (distanceRequiredToreachSourceStation * timerequiredByTrainToCoverOneKM);

			System.out.println("time reqruied for  source station  " + timeRequiredByTrainToreachSourceStation);
			trains.get(i).setPickUPTime(minuteToHoursConverter(timeRequiredByTrainToreachSourceStation));

			for (int j = 0; j < destinationStationIndex; j++) {
				// System.out.println("--"+j+"--");
				Route route = routeDAO.getrouteByStation(allStation.get(j), allStation.get(j + 1));
				// System.out.println(route.getDistance());
				distanceRequiredForDestinationStation += route.getDistance();
				timeRequiredByTrainForDestinationStation += 10;

			}

//          logic for time calculation
			timeRequiredByTrainForDestinationStation = timeRequiredByTrainForDestinationStation + timeInminutes
					+ (distanceRequiredForDestinationStation * timerequiredByTrainToCoverOneKM);
			System.out.println("time reqruied for destination station " + timeRequiredByTrainForDestinationStation);

			trains.get(i).setDropTime(minuteToHoursConverter(timeRequiredByTrainForDestinationStation));

			// for fair calculation for general purpose
			int distanceCoveredDuringJourney = distanceRequiredForDestinationStation
					- distanceRequiredToreachSourceStation;
			try {
				fair = this.calculateFareByTrainType(distanceCoveredDuringJourney, trains.get(i).getTrainType());
				trains.get(i).setFair(fair);
			} catch (Exception e) {
				System.err.print(e);
			}
			
			System.out.println("distance covered in whole journey" + distanceCoveredDuringJourney);
			
			trains.get(i).setFair(fair);
			trains.get(i).setTotalDistance(distanceCoveredDuringJourney);
		}
		return trains;
	}

	public int timeCounter(String time) {

		int hours = Integer.parseInt(time.split(":")[0]);
		int minutes = Integer.parseInt(time.split(":")[1]);
		int timeINMinutes = hours * 60 + minutes;

		return timeINMinutes;

	}

	public String minuteToHoursConverter(int minutes) {

		long minute = minutes;
		long hours = minute / 60;
		long minnutesRemaining = minutes % 60;
		String formatedTime = hours + ":" + minnutesRemaining;
		System.out.println(hours + "h" + minnutesRemaining + "minutes");
		return formatedTime;

	}
	
	@Override
	public double calculateFareByDistance(int distance, int fare) {
		if (distance < 100) {
			return (double)fare;
		} else {
			return (fare - fare * 0.2);
		}
	}
	
	@Override
	public int calculateFareByTrainType(int distance, String trainType) throws Exception{
		if (trainType.equals("Non AC Sleeper")) {
			return distance*3;
		} else if (trainType.equals("AC Sleeper")) {
			return distance*4;
		} else if (trainType.equals("Non AC Seater")) {
			return distance*2;
		} else if (trainType.equals("AC Seater")) {
			return distance*3;
		} else {
			throw new Exception("Invalid Train Type");
		}
	}
	
	@Override
	public double calculateFareByAge(int fare, int age) {
		if (age < 5) {
			return (fare * 0.5);
		} else if (age >= 60) {
			return (fare * 0.7);
		} else {
			return fare;
		}
	}

}
