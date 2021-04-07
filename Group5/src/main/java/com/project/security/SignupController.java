package com.project.security;

import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;
import com.project.user.UserConcreteFactory;

@Controller
public class SignupController {

	private final String USERNAME = "userName";
	private final String PASSWORD = "password";
	private final String CONFIRM_PASSWORD = "confirmPassword";
	private final String FIRST_NAME = "firstName";
	private final String LAST_NAME = "lastName";
	private final String GENDER = "gender";
	private final String DATE_OF_BIRTH = "dateOfBirth";
	private final String MOBILE_NUMBER = "mobileNumber";
	private final String SECURITY_QUESTION_ONE = "securityQuestionOne";
	private final String SECURITY_QUESTION_TWO = "securityQuestionTwo";
	private final String ANSWER_ONE = "answerone";
	private final String ANSWER_TWO = "answertwo";

	@RequestMapping("/signup")
	public String signUpPage(Model model) {	
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		IUser user = userAbstractFactory.createUser();
		SecurityQuestion securityQuestions = securityAbstractFactory.createSecurityQuestion();
		model.addAttribute(user);
		model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
		return "signup";
	}

	@RequestMapping(value = "/signup/save", method = RequestMethod.POST)
	public String signUpPage(@RequestParam(name = FIRST_NAME) String firstName,
			@RequestParam(name = LAST_NAME) String lastName, @RequestParam(name = GENDER) String gender,
			@RequestParam(name = DATE_OF_BIRTH, defaultValue = "2025-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
			@RequestParam(name = MOBILE_NUMBER) String mobileNubmer, @RequestParam(name = USERNAME) String userName,
			@RequestParam(name = PASSWORD) String password,
			@RequestParam(name = CONFIRM_PASSWORD) String confirmPassword,
			@RequestParam(name = SECURITY_QUESTION_ONE) String securityQuestionTwo,
			@RequestParam(name = SECURITY_QUESTION_TWO) String securityQuestionOne,
			@RequestParam(name = ANSWER_ONE) String answerOne, @RequestParam(name = ANSWER_TWO) String answerTwo,
			Model model) {

		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		IUserDAO userDAO = userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();

		boolean hasError = false;
		
		if (user.isFirstNameValid(firstName) == true || user.isLastNameValid(lastName) == true
				|| user.isPasswordEmpty(password) || user.isConfirmPasswordEmpty(confirmPassword)) {
			model.addAttribute("nameError", true);
			hasError = true;
		}
		if (user.isDateValid(dateOfBirth) == false) {
			model.addAttribute("dobError", true);
			hasError = true;
		}
		if (user.isPhoneNumberValid(mobileNubmer) == true) {
			model.addAttribute("mobileError", true);
			hasError = true;
		}
		if (user.isQuestionValid(securityQuestionOne, securityQuestionTwo) == false) {
			model.addAttribute("securityQuestionError", true);
			hasError = true;
		}
		if (user.isAnswerValid(answerOne) == true) {
			model.addAttribute("answerErrorOne", true);
			hasError = true;
		}
		if (user.isAnswerValid(answerTwo) == true) {
			model.addAttribute("answerErrorTwo", true);
			hasError = true;
		}
		if (user.isEmailIdValid(userName) == false) {
			model.addAttribute("emailError", true);
			hasError = true;
		}
		if (user.isPasswordValid(password, confirmPassword) == false) {
			model.addAttribute("passwordError", true);
			hasError = true;
		}
		if (userDAO.isUserExists(userName) == true) {
			model.addAttribute("userExists", true);
			hasError = true;
		}

		if (hasError) {
			UserAbstractFactory userAbstractFactory1 = new UserConcreteFactory();
			IUser newUser = userAbstractFactory1.createUser();
			SecurityQuestion securityQuestions = securityAbstractFactory.createSecurityQuestion();
			model.addAttribute(newUser);
			model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
			return "signup";
		} else {
			user.setFirstName(firstName);
			user.setLastName(lastName);
			user.setGender(gender);
			user.setDateOfBirth(dateOfBirth);
			user.setMobileNumber(mobileNubmer);
			user.setUserName(userName);
			user.setPassword(password);
			user.setQuestionOne(securityQuestionOne);
			user.setQuestionTwo(securityQuestionTwo);
			user.setAnswerOne(answerOne);
			user.setAnswerTwo(answerTwo);
			userDAO.saveUser(user);
			return "redirect:/login";	
		}
	}
}
