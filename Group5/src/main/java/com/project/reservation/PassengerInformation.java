package com.project.reservation;

public class PassengerInformation {
	public int passengerInformationId;
    public int reservationId;
    public String firstName;
    public String lastName;
    public String gender;
    public int age;
    public String berthPreference;
    public String seatNumber;
    public String coachNumber;
	public int getPassengerInformationId() {
		return passengerInformationId;
	}
	public void setPassengerInformationId(int passengerInformationId) {
		this.passengerInformationId = passengerInformationId;
	}
	public int getReservationId() {
		return reservationId;
	}
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getBerthPreference() {
		return berthPreference;
	}
	public void setBerthPreference(String berthPreference) {
		this.berthPreference = berthPreference;
	}
	public String getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	public String getCoachNumber() {
		return coachNumber;
	}
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
    
    
}
