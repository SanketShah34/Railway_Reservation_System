package com.project.calculation;

import java.util.List;
import org.springframework.stereotype.Component;
import com.project.setup.IRoute;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

@Component
public class FindFare implements IFindFare{
	
	public static final int minutesTrainStopAtStation = 10;

	int timeInminutes;
	
	public static final int minimumKiloMeterForDiscount = 100;
	public static final double discountForFair = 0.2;
	
	public static final String nonAcSleeperTrain = "Non AC Sleeper";
	public static final String acSleeper = "AC Sleeper";
	public static final String nonAcSeater = "Non AC Seater";
	public static final String acSeater = "AC Seater";
	
	public static final int fairForNonACSleeperTrainPerKiloMeter = 3;
	public static final int fairForAcSleeperPerKiloMeter = 4;
	public static final int fairFornonAcSeaterPerKiloMeter = 2;
	public static final int fairForAcSeaterPerKiloMeter = 3;

	public static final int minimumAgeForChildren = 0;
	public static final int maximumAgeForChildren = 5;
	public static final int minimumAgeForSeniorCitizen = 60;
	
	public static final double multiplierForChildren = 0.5;
	public static final double multiplierForSeniorCitizen = 0.7;
	
	public List<ITrain> findFareofTrainJourney(List<ITrain> trains, String sourceStation, String destinationStation, IRouteDAO routeDAO) {
		
		int totalTrain = trains.size();
		
		for (int i = 0; i < totalTrain ; i++) {
			
			String time = trains.get(i).getDepartureTime();

			timeInminutes = timeCounter(time);
			int timeRequiredByTrainToReachSourceStation = 0;
			int timeRequiredByTrainForDestinationStation = 0;
			int timerequiredByTrainToCoverOneKM = 1;
			int distanceRequiredToReachSourceStation = 0;
			int distanceRequiredForDestinationStation = 0;

			List<Integer> allStation = trains.get(i).getTotalStation();

			int sourceStationIndex = allStation.indexOf(Integer.parseInt(sourceStation));
			int destinationStationIndex = allStation.indexOf(Integer.parseInt(destinationStation));
			
			for (int k = 0; k < sourceStationIndex; k++) {
				IRoute route = routeDAO.getRouteByStation(allStation.get(k), allStation.get(k + 1));
				distanceRequiredToReachSourceStation += route.getDistance();
				timeRequiredByTrainToReachSourceStation += minutesTrainStopAtStation;
			}

			timeRequiredByTrainToReachSourceStation = timeRequiredByTrainToReachSourceStation + timeInminutes
					+ (distanceRequiredToReachSourceStation * timerequiredByTrainToCoverOneKM);

			
			trains.get(i).setPickUPTime(minuteToHoursConverter(timeRequiredByTrainToReachSourceStation));
			

			for (int j = 0; j < destinationStationIndex; j++) {
			
				IRoute route = routeDAO.getRouteByStation(allStation.get(j), allStation.get(j + 1));
				
				distanceRequiredForDestinationStation += route.getDistance();
				timeRequiredByTrainForDestinationStation += minutesTrainStopAtStation;

			}

		    //logic for time calculation
			timeRequiredByTrainForDestinationStation = timeRequiredByTrainForDestinationStation + timeInminutes
					+ (distanceRequiredForDestinationStation * timerequiredByTrainToCoverOneKM);
			

			trains.get(i).setDropTime(minuteToHoursConverter(timeRequiredByTrainForDestinationStation));

			//fair calculation for general purpose
			int distanceCoveredDuringJourney = distanceRequiredForDestinationStation - distanceRequiredToReachSourceStation;
		
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

	//reference https://stackoverflow.com/questions/5387371/how-to-convert-minutes-to-hours-and-minutes-hhmm-in-java/5387398
	public String minuteToHoursConverter(int minutes) {
		int minute = minutes;
        int finalhours = minute / 60;
        int finalminutes = minute % 60;
        String formatedTime = finalhours + ":" + minuteFormater(finalminutes);
        return formatedTime;
	}
	
	
	//reference https://stackoverflow.com/questions/60144953/how-to-convert-single-digit-integer-into-double-digit-javascript
	public String minuteFormater(int number) {
		  String minutesWithoutZero = String.valueOf(number);
		  String minutesWithZero;
		  if (minutesWithoutZero.length() == 1) {
			  minutesWithZero = '0' + minutesWithoutZero;  
		  }else {
			  minutesWithZero = minutesWithoutZero;
		  }
		  return minutesWithZero;
		}

	
	@Override
	public double calculateFareByDistance(int distance, double fare) {
		if (distance == 0) {
			return 0.0;
		}else if (distance < minimumKiloMeterForDiscount) {
			return (double)fare;
		} else {
			return (fare - fare * discountForFair);
		}
	}
	
	@Override
	public double calculateFareByTrainType(int distance, String trainType) throws Exception{
		if (trainType.equals(nonAcSleeperTrain)) {
			return distance*fairForNonACSleeperTrainPerKiloMeter;
		} else if (trainType.equals(acSleeper)) {
			return distance*fairForAcSleeperPerKiloMeter;
		} else if (trainType.equals(nonAcSeater)) {
			return distance*fairFornonAcSeaterPerKiloMeter;
		} else if (trainType.equals(acSeater)) {
			return distance*fairForAcSeaterPerKiloMeter;
		} else {
			throw new Exception("Invalid Train Type");
		}
	}
	
	@Override
	public double calculateFareByAge(double fare, int age) {
		if (age == minimumAgeForChildren) {
			return 0.0;
		}else if (age > minimumAgeForChildren && age < maximumAgeForChildren) {
			return (fare * multiplierForChildren);
		} else if (age >= minimumAgeForSeniorCitizen) {
			return (fare * multiplierForSeniorCitizen);
		} else {
			return fare;
		}
	}
}
