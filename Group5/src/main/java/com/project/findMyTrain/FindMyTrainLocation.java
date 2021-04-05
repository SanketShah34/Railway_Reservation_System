package com.project.findMyTrain;

import java.time.DayOfWeek;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.TextStyle;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import com.project.setup.ITrain;

public class FindMyTrainLocation implements IFindMyTrainLocation{

	@Override
	public void findMyTrainCalculation(ITrain train, Date startDate) {
		FindMyTrainAbstractFactory findMyTrainAbstractFactory = FindMyTrainAbstractFactory.instance();
		IFindMyTrainDAO findMyTrainDAO = findMyTrainAbstractFactory.createFindMyTrainDAO();
		List<Integer> stationIds = new ArrayList<Integer>();
        stationIds.add(Integer.valueOf(train.getStartStation()));
        String[] middleStationList = train.getMiddleStations().split(",");
        for(String middleStation : middleStationList) {
            stationIds.add(Integer.valueOf(middleStation));
        }
        stationIds.add(Integer.valueOf(train.getEndStation()));
       
        double totalDistance =0;    
        List<DistanceData> distanceDataList = new ArrayList<>();
        for(int i=0; i<stationIds.size()-1; i++) {
            double distance = findMyTrainDAO.getRouteInfo(stationIds.get(i), stationIds.get(i+1));
            totalDistance += distance;
            IDistanceData distanceData = findMyTrainAbstractFactory.createNewDistanceData();
            //DistanceData tempDistanceData = new DistanceData();
            distanceData.setStartStation(stationIds.get(i));
            distanceData.setEndStation(stationIds.get(i+1));
            distanceData.setDistance(distance);
            distanceDataList.add((DistanceData) distanceData);
        }
       
        Instant instant = startDate.toInstant();
        ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
        LocalDate date = zdt.toLocalDate();
       
        DayOfWeek day = date.getDayOfWeek();
        System.out.println(day);
       
        if(train.getDays().contains(day.getDisplayName(TextStyle.FULL, new Locale("EN")))) {
            LocalDateTime currentDateTime = LocalDateTime.now();
           
            String departureTimeOfTrain = train.getDepartureTime();
            LocalTime departureTime = LocalTime.parse(departureTimeOfTrain);
           
            LocalDateTime trainDepartureDateTime = LocalDateTime.of(date, departureTime);
           
            long minutes = ChronoUnit.MINUTES.between(currentDateTime, trainDepartureDateTime);
           
            long totalMinutesFromTotalDistance = Long.valueOf(String.valueOf(totalDistance * 1.5)) + ((distanceDataList.size()-1) * 5);
           
            if(totalMinutesFromTotalDistance <= minutes) {
                System.out.println("Train already reached at destination.");
            }
            else if(0 >= minutes) {
                System.out.println("Train has not departed from source.");
            }
            else {
                long tempMinutes = 0;
                for(int i=0; i< distanceDataList.size(); i++) {
                    tempMinutes +=  Long.valueOf(String.valueOf(distanceDataList.get(i).getDistance() * 1.5)) + 5;
                    if(tempMinutes > minutes) {
                        System.out.println("Train is between station : "+distanceDataList.get(i).getStartStation()
                                +" and station : "+distanceDataList.get(i).getEndStation());
                    }
                }
            }
        }
        else {
            System.out.println("No train running on that day.");
        }
		
	}

}
