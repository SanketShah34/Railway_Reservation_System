package com.project.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.project.dao.RouteDAO;
import com.project.entity.Route;
import com.project.entity.Train;


@Component
@ComponentScan("com.project.service")
public class findFareImpl implements findFare{

	@Autowired
	RouteDAO routeDAO;

	public List<Train> findFareofTrainjourney(List<Train> trains, String sourceStation, String destinationStation) {

		// routeDAO.listOfRoute();

		for (int i = 0; i < trains.size(); i++) {

		//	System.out.println("-------------------------for train" + i + 1 + "--------------------");
			String time = trains.get(i).getDepartureTime();

			int timeInminutes = timeCounter(time);
			int timeRequiredByTrainToreachSourceStation = 0;
			int timeRequiredByTrainForDestinationStation = 0;
			int timerequiredByTrainToCoverOneKM = 1;

			int distance = 0;
			int distanceRequiredToreachSourceStation = 0;
			int distanceRequiredForDestinationStation = 0;
			
			int fare = 0;

			List<Integer> allStation = trains.get(i).getTotalStation();

			int sourceStationIndex = allStation.indexOf(Integer.parseInt(sourceStation));
			int destinationStationIndex = allStation.indexOf(Integer.parseInt(destinationStation));

			System.out.println("total station ==" +allStation.size());
			
			for (int k = 0; k < sourceStationIndex; k++) {
				// System.out.println("--"+k+"--");
				Route route = routeDAO.getrouteByStation(allStation.get(k), allStation.get(k + 1));
				// System.out.println(route.getDistance());
				distanceRequiredToreachSourceStation += route.getDistance();
				timeRequiredByTrainToreachSourceStation += 10;

			}

			System.out.println("distance total train  to reach source station  cover " + distanceRequiredToreachSourceStation);
			System.out.println("timerequired by train to reach source station " + timeRequiredByTrainToreachSourceStation);
			System.out.println("time in minutes " + timeInminutes);
			System.out.println("distance time  " + (distanceRequiredToreachSourceStation * timerequiredByTrainToCoverOneKM));

			timeRequiredByTrainToreachSourceStation = timeRequiredByTrainToreachSourceStation + timeInminutes
					+ (distanceRequiredToreachSourceStation * timerequiredByTrainToCoverOneKM);

			System.out.println("time reqruied for  source station  " + timeRequiredByTrainToreachSourceStation);
			trains.get(i).setPickUPTime(minuteToHoursConverter(timeRequiredByTrainToreachSourceStation));
			
			System.out.println("====destination station index"+destinationStationIndex);
			

			for (int j = 0; j < destinationStationIndex; j++) {
			//	 System.out.println(allStation.get(j)+"--"+j+"--"+allStation.get(j + 1));
				Route route = routeDAO.getrouteByStation(allStation.get(j), allStation.get(j + 1));
				// System.out.println(route.getDistance());
				distanceRequiredForDestinationStation += route.getDistance();
				timeRequiredByTrainForDestinationStation += 10;

			}
			System.out.println("distance required for destination station "+distanceRequiredForDestinationStation);
			System.out.println("time required for destination station "+timeRequiredByTrainForDestinationStation);
//          logic for time calculation
			timeRequiredByTrainForDestinationStation = timeRequiredByTrainForDestinationStation + timeInminutes
					+ (distanceRequiredForDestinationStation * timerequiredByTrainToCoverOneKM);
		//	System.out.println("time reqruied for destination station " + timeRequiredByTrainForDestinationStation);

			trains.get(i).setDropTime(minuteToHoursConverter(timeRequiredByTrainForDestinationStation));

			// for fair calculation for general purpose
			int distanceCoveredDuringJourney = distanceRequiredForDestinationStation
					- distanceRequiredToreachSourceStation;
		//	System.out.println("distance covered in whole journey" + distanceCoveredDuringJourney);
			if (trains.get(i).getTrainType().equals("Non AC Sleeper")) {
				fare = distanceCoveredDuringJourney* 3;
			} else if (trains.get(i).getTrainType().equals("AC Sleeper")) {
				fare = distanceCoveredDuringJourney* 4;
			} else if (trains.get(i).getTrainType().equals("Non AC Seater")) {
				fare = distanceCoveredDuringJourney* 2;
			}
			else if (trains.get(i).getTrainType().equals("AC Seater")) {
				fare = distanceCoveredDuringJourney* 3;
			}
			
			trains.get(i).setFare(fare);

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
	//	System.out.println(hours + "h" + minnutesRemaining + "minutes");
		return formatedTime;

	}

}
