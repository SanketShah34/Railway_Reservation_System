package com.project.user;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.format.annotation.DateTimeFormat;

public class User implements IUser {

	private static final String EMAIL_REGEX = "^(.+)@(.+)$";

	public int id;

	@NotNull
	@Size(min = 2, max = 30, message = "should be more than 2")
	public String userName;

	public String password;
	public boolean enabled;
	public String role;

	@NotNull(message = "First Name may not be null")
	public String firstName;

	@NotNull(message = "Last Name may not be null")
	public String lastName;

	public String gender;

	@NotNull(message = "Date of birth may not be null")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Temporal(TemporalType.DATE)
	public Date dateOfBirth;

	@NotNull(message = "Mobile Number may not be null")
	public String mobileNumber;
	
	public String questionOne;
	
	public String answerOne;
	
	public String questionTwo;
	
	public String answerTwo;

	public User() {

	}

	public User(int id, String userName, String password, String role, boolean enabled, String firstName,
			String lastName, String gender, Date dateOfBirth, String mobileNumber) {
		this.id = id;
		this.userName = userName;
		this.password = password;
		this.enabled = enabled;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
		this.mobileNumber = mobileNumber;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
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

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public boolean passwordValidation(String password, String confirmPassword) {
		if(isStringNullOrEmpty(password)) {
			return false;
		}else {
			if (password.equals(confirmPassword)) {
				return true;
			} else {
				return false;
			}
		}
		
	}

	public boolean emailValidation(String email) {
		if (isStringNullOrEmpty(email)) {
			return false;
		}
		// String regex = "^(.+)@(.+)$";
		Pattern pattern = Pattern.compile(EMAIL_REGEX);
		Matcher matcher = pattern.matcher(email);
		if (matcher.matches() == true) {
			return true;
		} else {
			return false;
		}

	}

	// source:
	// https://stackoverflow.com/questions/14892536/to-check-if-the-date-is-after-the-specified-date
	public boolean dateValidation(Date date) {

		// https://stackoverflow.com/questions/11097256/how-to-convert-mon-jun-18-000000-ist-2012-to-18-06-2012
		String dateStr = date.toString();
		DateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss Z yyyy");
		Date dateParse;
		try {
			dateParse = (Date) formatter.parse(dateStr);

			Calendar cal = Calendar.getInstance();
			cal.setTime(dateParse);
			String formatedDate = cal.get(Calendar.DATE) + "/" + (cal.get(Calendar.MONTH) + 1) + "/"
					+ cal.get(Calendar.YEAR);

			String dateSplit[] = formatedDate.split("/");

			// https://mkyong.com/java8/java-check-if-the-date-is-older-than-6-months/
			LocalDate currentDate = LocalDate.now();
			LocalDate currentDateMinus180Months = currentDate.minusMonths(180);

			LocalDate date1 = LocalDate.of(Integer.parseInt(dateSplit[2]), Integer.parseInt(dateSplit[1]),
					Integer.parseInt(dateSplit[0]));

			if (date1.isBefore(currentDateMinus180Months)) {
				return true;
			} else {
				return false;
			}
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return true;

	}

	public boolean isStringNullOrEmpty(String s) {
		if (null == s) {
			return true;
		}
		return s.isEmpty();
	}

	public boolean isEmailIdValid(String emailId) {
		return emailValidation(emailId);
	}

	public boolean isFirstNameValid(String firstName) {
		return isStringNullOrEmpty(firstName);
	}

	public boolean isLastNameValid(String lastName) {
		return isStringNullOrEmpty(lastName);
	}

	public boolean isPasswordEmpty(String password) {
		return isStringNullOrEmpty(password);
	}

	public boolean isConfirmPasswordEmpty(String confirmPassword) {
		return isStringNullOrEmpty(confirmPassword);
	}

	public boolean isDateValid(Date date) {
		return dateValidation(date);
	}
	
	public boolean isPhoneNumberValid(String number) {
		return isStringNullOrEmpty(number);
	}

	public boolean isPasswordValid(String password, String confirmPassword) {
		return passwordValidation(password, confirmPassword);
	}

	public boolean isQuestionValid(String questionOne, String questionTwo) {
		if (questionOne.equals(questionTwo)) {
			return false;
		}
		return true;
	}

	

	@Override
	public boolean isAnswerValid(String answer) {
		return isStringNullOrEmpty(answer);
	}

	public String getQuestionOne() {
		return questionOne;
	}

	public void setQuestionOne(String questionOne) {
		this.questionOne = questionOne;
	}

	public String getAnswerOne() {
		return answerOne;
	}

	public void setAnswerOne(String answerOne) {
		this.answerOne = answerOne;
	}

	public String getQuestionTwo() {
		return questionTwo;
	}

	public void setQuestionTwo(String questionTwo) {
		this.questionTwo = questionTwo;
	}

	public String getAnswerTwo() {
		return answerTwo;
	}

	public void setAnswerTwo(String answerTwo) {
		this.answerTwo = answerTwo;
	}

	public static String getEmailRegex() {
		return EMAIL_REGEX;
	}


}
