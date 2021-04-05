package com.project.lookup;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.project.setup.IRoute;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;

public class TrainFilterAndFairCalculation implements ITrainFilterAndFairCalculation {
	public static final int minutesTrainStopAtStation = 10;
	public static final int minutesIn24hours = 1440;
	public static final int minimumKiloMeterForDiscount = 100;
	public static final double discountForFair = 0.2;
	public static final String nonAcSleeperTrainString = "Non AC Sleeper";
	public static final String acSleeperString = "AC Sleeper";
	public static final String nonAcSeaterString = "Non AC Seater";
	public static final String acSeaterString = "AC Seater";
	public static final int fairForNonACSleeperTrainPerKiloMeter = 3;
	public static final int fairForAcSleeperPerKiloMeter = 4;
	public static final int fairFornonAcSeaterPerKiloMeter = 2;
	public static final int fairForAcSeaterPerKiloMeter = 3;
	public static final int minimumAgeForChildren = 0;
	public static final int maximumAgeForChildren = 5;
	public static final int minimumAgeForSeniorCitizen = 60;
	public static final double multiplierForChildren = 0.5;
	public static final double multiplierForSeniorCitizen = 0.7;
	double timeRequiredByTrainToCoverOneKM = 1;

	@Override
	public List<ITrain> filterTrain(List<ITrain> trains, ISearchTrain searchTrain, IRouteDAO routeDAO) {
		String timeTrainLeavesStartStationInHour = "0.00";
		double timeRequiredByTrainToReachSourceStationInMinutes = 0;
		double timeRequiredByTrainToDestinationStationInMinutes = 0;
		double timeTrainLeavesStartStationInMinutes = 0;
		double distanceRequiredToReachSourceStationInKm = 0;
		double distanceRequiredForDestinationStationInKm = 0;
		double distanceCoveredDuringJourneyInKm = 0;
		String sourceStaion = null;
		String destinationStation = null;
		int sourceStationIndex = 0;
		int destinationStationIndex = 0;
		boolean trainIsavailableOrNot = true;
		double fare = 0.0;
		int totalTrainFromDB = trains.size();
		 
		List<Integer> trainToBeRemoved = new ArrayList<Integer>();
		
		for (int i = 0; i < totalTrainFromDB; i++) {
			
			timeTrainLeavesStartStationInHour = trains.get(i).getDepartureTime();
			timeTrainLeavesStartStationInMinutes = hoursToMinuteConverter(timeTrainLeavesStartStationInHour);
			List<Integer> allStationTrainVisit = trains.get(i).getTotalStation();
			sourceStaion = searchTrain.getSourceStation();
			destinationStation = searchTrain.getDestinationStation();
			
			sourceStationIndex = allStationTrainVisit.indexOf(Integer.parseInt(sourceStaion));
			
			destinationStationIndex = allStationTrainVisit.indexOf(Integer.parseInt(destinationStation));
			for (int k = 0; k < sourceStationIndex; k++) {
				IRoute route = routeDAO.getRouteByStation(allStationTrainVisit.get(k), allStationTrainVisit.get(k + 1));
				distanceRequiredToReachSourceStationInKm += route.getDistance();
				timeRequiredByTrainToReachSourceStationInMinutes += minutesTrainStopAtStation;
			}
			timeRequiredByTrainToReachSourceStationInMinutes = timeRequiredByTrainToReachSourceStationInMinutes
					+ (distanceRequiredToReachSourceStationInKm * timeRequiredByTrainToCoverOneKM);
			for (int j = 0; j < destinationStationIndex; j++) {
				IRoute route = routeDAO.getRouteByStation(allStationTrainVisit.get(j), allStationTrainVisit.get(j + 1));
				distanceRequiredForDestinationStationInKm += route.getDistance();
				timeRequiredByTrainToDestinationStationInMinutes += minutesTrainStopAtStation;
			}
			timeRequiredByTrainToDestinationStationInMinutes = timeRequiredByTrainToDestinationStationInMinutes
					+ (distanceRequiredForDestinationStationInKm * timeRequiredByTrainToCoverOneKM);
			calculateStartDateOfTrain(trains.get(i), searchTrain, timeRequiredByTrainToReachSourceStationInMinutes,
					timeTrainLeavesStartStationInMinutes);
			trainIsavailableOrNot = countPickUpAndDropUpTimeAndTrainIsAvailbleOnThatDayOrNot(
					timeTrainLeavesStartStationInMinutes, timeRequiredByTrainToReachSourceStationInMinutes,
					timeRequiredByTrainToDestinationStationInMinutes, trains.get(i), searchTrain);
			if (trainIsavailableOrNot) {
				distanceCoveredDuringJourneyInKm = distanceRequiredForDestinationStationInKm
						- distanceRequiredToReachSourceStationInKm;
				trains.get(i).setTotalDistance(distanceCoveredDuringJourneyInKm);
				try {
					fare = this.calculateFareByTrainType(distanceCoveredDuringJourneyInKm,
							trains.get(i).getTrainType());
					trains.get(i).setFare(fare);
				} catch (Exception e) {
					System.err.print(e);
				}
				continue;
			}
			 else {
				 trainToBeRemoved.add(i);
			}
		}
		
		for(int t=  trainToBeRemoved.size() -1 ; t  >=0  ; t--) {
			trains.remove(trains.get(trainToBeRemoved.get(t)));
		}
		return trains;
	}
	


	@Override
	public boolean countPickUpAndDropUpTimeAndTrainIsAvailbleOnThatDayOrNot(double timeAtTrainStartItsJourneyInMinutes,
			double timeRequiredByTrainToReachSourceStationInMinutes,
			double timeRequiredByTrainForDestinationStationInMinutes, ITrain train, ISearchTrain searchTrain) {
		boolean istrainAvailableOnThatDate = true;
		double timeBetweenSourceAndDestinationStationInMinutes = 0;
		double totalTimeBetweenStartStationAndSourceStationInMinutes = 0;
		int daytoIncrementInSourceStation = 0;
		int daytoIncrementInDestinationStation = 0;
		double timeAtTrainReachSourceStationInMinutes = 0;
		double timeLeftInFirstDayOfTrainInMinutes = 0;
		double timeAtTrainWillReachDestinationStationInMinutes = 0;
		double timeNeedToCoverForOtherDestinationInMinutes = 0;
		double timeAtTrainReachDestinationStationInMinutes = 0;
		double timeLeftInDayAfterReachingSourceStationInMinutes = 0;
		boolean trainTravelOnUserdayWhenUserWant = true;
		String timeAtTrainReachSourceStationInHours = "0:00";
		String timeAtTrainReachDestinationStationStationInHours = "0:00";
		timeBetweenSourceAndDestinationStationInMinutes = timeRequiredByTrainForDestinationStationInMinutes
				- timeRequiredByTrainToReachSourceStationInMinutes;
		totalTimeBetweenStartStationAndSourceStationInMinutes = timeAtTrainStartItsJourneyInMinutes
				+ timeRequiredByTrainToReachSourceStationInMinutes;
		daytoIncrementInSourceStation = 0;
		daytoIncrementInDestinationStation = 0;
		timeAtTrainReachSourceStationInMinutes = 0;
		timeLeftInFirstDayOfTrainInMinutes = minutesIn24hours - timeAtTrainStartItsJourneyInMinutes;
		timeAtTrainWillReachDestinationStationInMinutes = 0;
		timeNeedToCoverForOtherDestinationInMinutes = 0;
		timeAtTrainReachDestinationStationInMinutes = 0;
		timeLeftInDayAfterReachingSourceStationInMinutes = 0;
		if (timeLeftInFirstDayOfTrainInMinutes > timeRequiredByTrainToReachSourceStationInMinutes) {
			daytoIncrementInSourceStation = 0;
			timeAtTrainReachSourceStationInMinutes = totalTimeBetweenStartStationAndSourceStationInMinutes;
			timeLeftInDayAfterReachingSourceStationInMinutes = minutesIn24hours
					- timeAtTrainReachSourceStationInMinutes;
			trainTravelOnUserdayWhenUserWant = checkWhetherTrainIsAvailableOrNotOnThatDay(train,
					daytoIncrementInSourceStation, searchTrain);
			if (trainTravelOnUserdayWhenUserWant) {
				timeAtTrainReachSourceStationInHours = minuteToHoursConverter(timeAtTrainReachSourceStationInMinutes);
				train.setPickUPTime(timeAtTrainReachSourceStationInHours);
				
				java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateofJourny().getTime());
				
				train.setPickUPDate(sqlDate);
			} else {
				istrainAvailableOnThatDate = false;
			}
		} else {
			daytoIncrementInSourceStation = (int) (totalTimeBetweenStartStationAndSourceStationInMinutes
					/ minutesIn24hours);
			timeAtTrainReachSourceStationInMinutes = totalTimeBetweenStartStationAndSourceStationInMinutes
					% minutesIn24hours;
			timeLeftInDayAfterReachingSourceStationInMinutes = minutesIn24hours
					- timeAtTrainReachSourceStationInMinutes;
			trainTravelOnUserdayWhenUserWant = checkWhetherTrainIsAvailableOrNotOnThatDay(train,
					daytoIncrementInSourceStation, searchTrain);
			if (trainTravelOnUserdayWhenUserWant) {
				timeAtTrainReachSourceStationInHours = minuteToHoursConverter(timeAtTrainReachSourceStationInMinutes);
				train.setPickUPTime(timeAtTrainReachSourceStationInHours);
				
				java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateofJourny().getTime());
				train.setPickUPDate(sqlDate);
			} else {
				istrainAvailableOnThatDate = false;
				return istrainAvailableOnThatDate;
			}
		}
		if (timeLeftInDayAfterReachingSourceStationInMinutes > timeBetweenSourceAndDestinationStationInMinutes) {
			timeAtTrainReachDestinationStationInMinutes = timeBetweenSourceAndDestinationStationInMinutes
					+ timeAtTrainReachSourceStationInMinutes;
			timeAtTrainReachDestinationStationStationInHours = minuteToHoursConverter(
					timeAtTrainReachDestinationStationInMinutes);
			train.setDropTime(timeAtTrainReachDestinationStationStationInHours);
			daytoIncrementInDestinationStation = 0;
			SetDateForDropUp(train, daytoIncrementInDestinationStation, searchTrain);
		} else {
			timeNeedToCoverForOtherDestinationInMinutes = timeAtTrainStartItsJourneyInMinutes
					+ timeRequiredByTrainForDestinationStationInMinutes;
			daytoIncrementInDestinationStation = (int) (timeNeedToCoverForOtherDestinationInMinutes / minutesIn24hours);
			SetDateForDropUp(train, daytoIncrementInDestinationStation, searchTrain);
			timeAtTrainWillReachDestinationStationInMinutes = timeNeedToCoverForOtherDestinationInMinutes
					% minutesIn24hours;
			timeAtTrainReachSourceStationInHours = minuteToHoursConverter(
					timeAtTrainWillReachDestinationStationInMinutes);
			train.setDropTime(timeAtTrainReachSourceStationInHours);
		}
		return istrainAvailableOnThatDate;
	}

	@Override
	public void calculateStartDateOfTrain(ITrain train, ISearchTrain searchTrain,
			double timeRequiredByTrainToReachSourceStation, double trainStarttime) {
		double totalTimeIncludingTrainStartTime = 0;
		int dayToBeRemoved = 0;
		totalTimeIncludingTrainStartTime = timeRequiredByTrainToReachSourceStation + trainStarttime;
		dayToBeRemoved = (int) (totalTimeIncludingTrainStartTime / minutesIn24hours);
		setStartDateForTrain(train, dayToBeRemoved, searchTrain);

	}

	// reference
	// https://stackoverflow.com/questions/5387371/how-to-convert-minutes-to-hours-and-minutes-hhmm-in-java/5387398
	@Override
	public String minuteToHoursConverter(double minutes) {
		double minute = 0;
		int finalhours = 0;
		double finalminutes = 0;
		String formatedTime = "0:00";
		minute = minutes;
		finalhours = (int) minute / 60;
		finalminutes = minute % 60;
		formatedTime = finalhours + ":" + minuteFormater(finalminutes);
		return formatedTime;
	}

	// reference
	// https://stackoverflow.com/questions/60144953/how-to-convert-single-digit-integer-into-double-digit-javascript
	@Override
	public String minuteFormater(double number) {
		String minutesWithoutZero = "00";
		String minutesWithZero = "0";
		minutesWithoutZero = String.valueOf((int) number);
		if (minutesWithoutZero.length() == 1) {
			minutesWithZero = '0' + String.valueOf(Integer.parseInt(minutesWithoutZero));
		} else {
			minutesWithZero = String.valueOf(Integer.parseInt(minutesWithoutZero));
		}
		return minutesWithZero;
	}

	@Override
	public boolean checkWhetherTrainIsAvailableOrNotOnThatDay(ITrain train, int daytoIncrement,
			ISearchTrain searchTrain ) {
		
		
		
		String days = "";
		String[] daysArray = null;
		String dayUserWantTotravel = "";
		boolean trainTravelThatDayOrNot = false;
		days = train.getDays();
		daysArray = days.split(",");
		DaysCalculation daysCalculation = new DaysCalculation();
		for (int i = 0; i < daysArray.length; i++) {
			daysArray[i] = daysCalculation.getDay(daysArray[i], daytoIncrement);
		}
		
		java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateofJourny().getTime());
		
		
		
		dayUserWantTotravel = getDaysNameFromDate(sqlDate);
		trainTravelThatDayOrNot = false;
		for (int j = 0; j < daysArray.length; j++) {
			if (daysArray[j].equals(dayUserWantTotravel)) {
				trainTravelThatDayOrNot = true;
				break;
			}
		}
		return trainTravelThatDayOrNot;
	}

	@Override
	public double hoursToMinuteConverter(String timeInMinute) {
		double hours = 0;
		double minutes = 0;
		double timeInMinutes = 0;
		hours = Integer.parseInt(timeInMinute.split(":")[0]);
		minutes = Integer.parseInt(timeInMinute.split(":")[1]);
		timeInMinutes = hours * 60 + minutes;
		return timeInMinutes;
	}

	// reference :
	// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
	@Override
	public void setStartDateForTrain(ITrain train, int dayToRemove, ISearchTrain searchTrain) {
		java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateofJourny().getTime());
		
		Date date = Date.valueOf(sqlDate.toLocalDate().minusDays(dayToRemove));
		train.setStartDate(date);
	}

	// reference :
	// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
	@Override
	public void SetDateForDropUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain) {
		Date date = Date.valueOf(train.getStartDate().toLocalDate().plusDays(dayTOIncrement));
		train.setDropUpDate(date);
	}

	@Override
	public void SetDateForPickUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain) {
		java.sql.Date sqlDate = new java.sql.Date(searchTrain.getDateofJourny().getTime());
		Date date = Date.valueOf(sqlDate.toLocalDate().plusDays(dayTOIncrement));
		train.setPickUPDate(date);
	}

	@Override
	public String getDaysNameFromDate(Date dateToBeformate) {
		final Date currentTime = dateToBeformate;
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		return simpleDateformat.format(currentTime);
	}

	@Override
	public double calculateFareByDistance(double distance, double fare) {
		if (distance == 0) {
			return 0.0;
		} else if (distance < minimumKiloMeterForDiscount) {
			return (double) fare;
		} else {
			double discount = fare * discountForFair;
			double finalFare = fare - discount;
			return finalFare;
		}
	}

	@Override
	public double calculateFareByTrainType(double distance, String trainType) throws Exception {
		if (trainType.equals(nonAcSleeperTrainString)) {
			return distance * fairForNonACSleeperTrainPerKiloMeter;
		} else if (trainType.equals(acSleeperString)) {
			return distance * fairForAcSleeperPerKiloMeter;
		} else if (trainType.equals(nonAcSeaterString)) {
			return distance * fairFornonAcSeaterPerKiloMeter;
		} else if (trainType.equals(acSeaterString)) {
			return distance * fairForAcSeaterPerKiloMeter;
		} else {
			throw new Exception("Invalid Train Type");
		}
	}

	@Override
	public double calculateFareByAge(double fare, int age) {
		if (age == minimumAgeForChildren) {
			return 0.0;
		} else if (age > minimumAgeForChildren && age < maximumAgeForChildren) {
			return (fare * multiplierForChildren);
		} else if (age >= minimumAgeForSeniorCitizen) {
			return (fare * multiplierForSeniorCitizen);
		} else {
			return fare;
		}
	}

}
