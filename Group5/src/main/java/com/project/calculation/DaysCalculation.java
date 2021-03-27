package com.project.calculation;

import java.util.ArrayList;

public class DaysCalculation {

	int currentIndex = 0;
	String dayNameFromDayClass = null;
	int newIndex = 0;
	String dayAfterIncrement = null;

	public class Day {
		public int dayIndex;
		public String dayName;

		public Day(int dayIndex, String dayName) {
			this.dayIndex = dayIndex;
			this.dayName = dayName;
		}
	}

	ArrayList<Day> dayList = new ArrayList<Day>();

	public DaysCalculation() {
		dayList.add(new Day(0, "Sunday"));
		dayList.add(new Day(1, "Monday"));
		dayList.add(new Day(2, "Tuesday"));
		dayList.add(new Day(3, "Wednesday"));
		dayList.add(new Day(4, "Thursday"));
		dayList.add(new Day(5, "Friday"));
		dayList.add(new Day(6, "Saturday"));
	}

	public String getDay(String trainStartDay, int increment) {
		currentIndex = 0;
		for (int i = 0; i < dayList.size(); i++) {
			dayNameFromDayClass = dayList.get(i).dayName.toLowerCase();
			if (trainStartDay.toLowerCase().equals(dayNameFromDayClass)) {
				currentIndex = i;
				break;
			}
		}
		newIndex = currentIndex + increment;
		if (newIndex > 6) {
			newIndex = newIndex - 7;
		}
		dayAfterIncrement = dayList.get(newIndex).dayName;
		return dayAfterIncrement;
	}

}
