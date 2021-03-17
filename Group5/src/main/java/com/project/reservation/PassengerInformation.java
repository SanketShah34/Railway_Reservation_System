package com.project.reservation;

public class PassengerInformation implements IPassengerInformation {
	public int passengerInformationId;
    public int reservationId;
    public String firstName;
    public String lastName;
    public String gender;
    public int age;
    public String berthPreference;
    public String seatNumber;
    public String coachNumber;
    public double amountPaid;
    
	@Override
	public double getAmountPaid() {
		return amountPaid;
	}
	@Override
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}
	@Override
	public int getPassengerInformationId() {
		return passengerInformationId;
	}
	@Override
	public void setPassengerInformationId(int passengerInformationId) {
		this.passengerInformationId = passengerInformationId;
	}
	@Override
	public int getReservationId() {
		return reservationId;
	}
	@Override
	public void setReservationId(int reservationId) {
		this.reservationId = reservationId;
	}
	@Override
	public String getFirstName() {
		return firstName;
	}
	@Override
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	@Override
	public String getLastName() {
		return lastName;
	}
	@Override
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	@Override
	public String getGender() {
		return gender;
	}
	@Override
	public void setGender(String gender) {
		this.gender = gender;
	}
	@Override
	public int getAge() {
		return age;
	}
	@Override
	public void setAge(int age) {
		this.age = age;
	}
	@Override
	public String getBerthPreference() {
		return berthPreference;
	}
	@Override
	public void setBerthPreference(String berthPreference) {
		this.berthPreference = berthPreference;
	}
	@Override
	public String getSeatNumber() {
		return seatNumber;
	}
	@Override
	public void setSeatNumber(String seatNumber) {
		this.seatNumber = seatNumber;
	}
	@Override
	public String getCoachNumber() {
		return coachNumber;
	}
	@Override
	public void setCoachNumber(String coachNumber) {
		this.coachNumber = coachNumber;
	}
    
    
}
