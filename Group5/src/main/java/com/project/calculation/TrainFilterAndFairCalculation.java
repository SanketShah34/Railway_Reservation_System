package com.project.calculation;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;
import com.project.lookup.ISearchTrain;
import com.project.setup.IRoute;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;

public class TrainFilterAndFairCalculation implements ITrainFilterAndCalculation {

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
	String timeTrainLeavesStartStationInHour = "0.00";
	double timeTrainLeavesStartStationInMinutes;
	double timeRequiredByTrainToReachSourceStationInMinutes = 0;
	double timeRequiredByTrainToDestinationStationInMinutes = 0;
	double timeRequiredByTrainToCoverOneKM = 1;
	double distanceRequiredToReachSourceStation = 0;
	double distanceRequiredForDestinationStation = 0;
	double distanceCoveredDuringJourney = 0;
	String sourceStaion;
	String destinationStation;
	int sourceStationIndex = 0;
	int destinationStationIndex = 0;
	boolean trainIsavailableOrNot = true;
	double fare = 0.0;
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
	double totalTimeIncludingTrainStartTime = 0;
	int dayToBeRemoved = 0;
	double minute = 0;
	int finalhours = 0;
	double finalminutes = 0;
	String formatedTime = "0:00";
	String minutesWithoutZero = "00";
	String minutesWithZero = "0";
	String days = "";
	String[] daysArray = null;
	String dayUserWantTotravel = "";
	boolean trainTravelThatDayOrNot = false;
	double hours = 0;
	double minutes = 0;
	double timeInMinutes = 0;

	public List<ITrain> filterTrain(List<ITrain> trains, ISearchTrain searchTrain, IRouteDAO routeDAO) {
		int totalTrainFromDB = trains.size();
		for (int i = 0; i < totalTrainFromDB; i++) {
			timeTrainLeavesStartStationInHour = trains.get(i).getDepartureTime();
			timeTrainLeavesStartStationInMinutes = hoursToMinuteConverter(timeTrainLeavesStartStationInHour);
			timeRequiredByTrainToReachSourceStationInMinutes = 0;
			timeRequiredByTrainToDestinationStationInMinutes = 0;
			timeRequiredByTrainToCoverOneKM = 1;
			distanceRequiredToReachSourceStation = 0;
			distanceRequiredForDestinationStation = 0;
			List<Integer> allStationTrainVisit = trains.get(i).getTotalStation();
			sourceStaion = searchTrain.getSourceStation();
			destinationStation = searchTrain.getDestinationStation();
			sourceStationIndex = allStationTrainVisit.indexOf(Integer.parseInt(sourceStaion));
			destinationStationIndex = allStationTrainVisit.indexOf(Integer.parseInt(destinationStation));
			for (int k = 0; k < sourceStationIndex; k++) {
				IRoute route = routeDAO.getRouteByStation(allStationTrainVisit.get(k), allStationTrainVisit.get(k + 1));
				distanceRequiredToReachSourceStation += route.getDistance();
				timeRequiredByTrainToReachSourceStationInMinutes += minutesTrainStopAtStation;
			}
			timeRequiredByTrainToReachSourceStationInMinutes = timeRequiredByTrainToReachSourceStationInMinutes
					+ (distanceRequiredToReachSourceStation * timeRequiredByTrainToCoverOneKM);
			for (int j = 0; j < destinationStationIndex; j++) {
				IRoute route = routeDAO.getRouteByStation(allStationTrainVisit.get(j), allStationTrainVisit.get(j + 1));
				distanceRequiredForDestinationStation += route.getDistance();
				timeRequiredByTrainToDestinationStationInMinutes += minutesTrainStopAtStation;
			}
			timeRequiredByTrainToDestinationStationInMinutes = timeRequiredByTrainToDestinationStationInMinutes
					+ (distanceRequiredForDestinationStation * timeRequiredByTrainToCoverOneKM);
			calculateStartDateOfTrain(trains.get(i), searchTrain, timeRequiredByTrainToReachSourceStationInMinutes,
					timeTrainLeavesStartStationInMinutes);
			trainIsavailableOrNot = countPickUpAndDropUpTime(timeTrainLeavesStartStationInMinutes,
					timeRequiredByTrainToReachSourceStationInMinutes, timeRequiredByTrainToDestinationStationInMinutes,
					trains.get(i), searchTrain);
			if (trainIsavailableOrNot) {
				distanceCoveredDuringJourney = distanceRequiredForDestinationStation
						- distanceRequiredToReachSourceStation;
				trains.get(i).setTotalDistance(distanceCoveredDuringJourney);
				try {
					fare = this.calculateFareByTrainType(distanceCoveredDuringJourney, trains.get(i).getTrainType());
					trains.get(i).setFare(fare);
				} catch (Exception e) {
					System.err.print(e);
				}
				continue;
			} else {
				trains.remove(i);
			}
		}
		return trains;
	}

	public boolean countPickUpAndDropUpTime(double timeAtTrainStartItsJourneyInMinutes,
			double timeRequiredByTrainToReachSourceStationInMinutes,
			double timeRequiredByTrainForDestinationStationInMinutes, ITrain train, ISearchTrain searchTrain) {
		istrainAvailableOnThatDate = true;
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
				train.setPickUPDate(searchTrain.getDateofJourny());
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
				train.setPickUPDate(searchTrain.getDateofJourny());
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

	public void calculateStartDateOfTrain(ITrain train, ISearchTrain searchTrain,
			double timeRequiredByTrainToReachSourceStation, double trainStarttime) {
		totalTimeIncludingTrainStartTime = timeRequiredByTrainToReachSourceStation + trainStarttime;
		dayToBeRemoved = (int) (totalTimeIncludingTrainStartTime / minutesIn24hours);
		setStartDateForTrain(train, dayToBeRemoved, searchTrain);
	}

	// reference
	// https://stackoverflow.com/questions/5387371/how-to-convert-minutes-to-hours-and-minutes-hhmm-in-java/5387398
	public String minuteToHoursConverter(double minutes) {
		minute = minutes;
		finalhours = (int) minute / 60;
		finalminutes = minute % 60;
		formatedTime = finalhours + ":" + minuteFormater(finalminutes);
		return formatedTime;
	}

	// reference
	// https://stackoverflow.com/questions/60144953/how-to-convert-single-digit-integer-into-double-digit-javascript
	public String minuteFormater(double number) {
		minutesWithoutZero = String.valueOf((int) number);
		if (minutesWithoutZero.length() == 1) {
			minutesWithZero = '0' + String.valueOf(Integer.parseInt(minutesWithoutZero));
		} else {
			minutesWithZero = String.valueOf(Integer.parseInt(minutesWithoutZero));
		}
		return minutesWithZero;
	}

	public boolean checkWhetherTrainIsAvailableOrNotOnThatDay(ITrain train, int daytoIncrement,
			ISearchTrain searchTrain) {
		days = train.getDays();
		daysArray = days.split(",");
		DaysCalculation daysCalculation = new DaysCalculation();
		for (int i = 0; i < daysArray.length; i++) {
			daysArray[i] = daysCalculation.getDay(daysArray[i], daytoIncrement);
		}
		dayUserWantTotravel = getDaysNameFromDate(searchTrain.getDateofJourny());
		trainTravelThatDayOrNot = false;
		for (int j = 0; j < daysArray.length; j++) {
			if (daysArray[j].equals(dayUserWantTotravel)) {
				trainTravelThatDayOrNot = true;
				break;
			}
		}
		return trainTravelThatDayOrNot;
	}

	public double hoursToMinuteConverter(String time) {
		hours = Integer.parseInt(time.split(":")[0]);
		minutes = Integer.parseInt(time.split(":")[1]);
		timeInMinutes = hours * 60 + minutes;
		return timeInMinutes;
	}

	// reference :
	// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
	public void setStartDateForTrain(ITrain train, int dayToRemove, ISearchTrain searchTrain) {
		Date date = Date.valueOf(searchTrain.getDateofJourny().toLocalDate().minusDays(dayToRemove));
		train.setStartDate(date);
	}

	// reference :
	// https://stackoverflow.com/questions/1005523/how-to-add-one-day-to-a-date
	public void SetDateForDropUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain) {
		Date date = Date.valueOf(train.getStartDate().toLocalDate().plusDays(dayTOIncrement));
		train.setDropUpDate(date);
	}

	public void SetDateForPickUp(ITrain train, int dayTOIncrement, ISearchTrain searchTrain) {
		Date date = Date.valueOf(searchTrain.getDateofJourny().toLocalDate().plusDays(dayTOIncrement));
		train.setPickUPDate(date);
	}

	public String getDaysNameFromDate(Date dateToBeformate) {
		final Date currentTime = dateToBeformate;
		SimpleDateFormat simpleDateformat = new SimpleDateFormat("EEEE");
		return simpleDateformat.format(currentTime);
	}

	public double calculateFareByDistance(double distance, double fare) {
		if (distance == 0) {
			return 0.0;
		} else if (distance < minimumKiloMeterForDiscount) {
			return (double) fare;
		} else {
			return (fare - fare * discountForFair);
		}
	}

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
