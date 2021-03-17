package com.project.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import com.project.setup.IRoute;
import com.project.setup.IRouteDAO;
import com.project.setup.Route;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.Train;


@Component
@ComponentScan("com.project.service")
public class findFareImpl implements findFare{

	public List<Train> findFareofTrainjourney(List<Train> trains, String sourceStation, String destinationStation) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createRouteDAO();

		for (int i = 0; i < trains.size(); i++) {
			String time = trains.get(i).getDepartureTime();

			int timeInminutes = timeCounter(time);
			int timeRequiredByTrainToreachSourceStation = 0;
			int timeRequiredByTrainForDestinationStation = 0;
			int timerequiredByTrainToCoverOneKM = 1;

			int distance = 0;
			int distanceRequiredToreachSourceStation = 0;
			int distanceRequiredForDestinationStation = 0;
			

			List<Integer> allStation = trains.get(i).getTotalStation();

			int sourceStationIndex = allStation.indexOf(Integer.parseInt(sourceStation));
			int destinationStationIndex = allStation.indexOf(Integer.parseInt(destinationStation));

			System.out.println("total station ==" +allStation.size());
			
			for (int k = 0; k < sourceStationIndex; k++) {
				// System.out.println("--"+k+"--");
				IRoute route = routeDAO.getrouteByStation(allStation.get(k), allStation.get(k + 1));
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
				IRoute route = routeDAO.getrouteByStation(allStation.get(j), allStation.get(j + 1));
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
			try {
				double fare = this.calculateFareByTrainType(distanceCoveredDuringJourney, trains.get(i).getTrainType());
				trains.get(i).setFare(fare);
			} catch (Exception e) {
				System.err.print(e);
			}
			
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
	//	System.out.println(hours + "h" + minnutesRemaining + "minutes");
		return formatedTime;

	}

	
	@Override
	public double calculateFareByDistance(int distance, double fare) {
		if (distance < 100) {
			return (double)fare;
		} else {
			return (fare - fare * 0.2);
		}
	}
	
	@Override
	public double calculateFareByTrainType(int distance, String trainType) throws Exception{
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
	public double calculateFareByAge(double fare, int age) {
		if (age > 0 && age < 5) {
			return (fare * 0.5);
		} else if (age >= 60) {
			return (fare * 0.7);
		} else {
			return fare;
		}
	}
}
