package com.project.findMyTrain;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.lookup.ISearchTrain;
import com.project.lookup.LookupAbstractFactory;
import com.project.security.SecurityAbstractFactory;
import com.project.setup.ITrain;
import com.project.user.IUser;
import com.project.user.UserAbstractFactory;
import com.project.user.UserConcreteFactory;

@Controller
public class FindMyTrainController {
	
	private final String TRAIN_CODE = "trainCode";
	private final String START_DATE = "startDate";

	@RequestMapping("/findMyTrain")
	public String findMyTrain(Model model) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ISearchTrain searchTrain =lookupAbstractFactory.createNewSearchTrain();
		System.out.println("findMyTrain");
		model.addAttribute(searchTrain);
		return "findMyTrain/findMyTrain";
	}
	
	@RequestMapping(value = "/findMyTrain/location", method = RequestMethod.POST)
	public String findLocation(@RequestParam(name = TRAIN_CODE) int trainCode,
								@RequestParam(name = START_DATE) String startDateString,
								Model model) {
		
		System.out.println("findMyTrainLocation");
		FindMyTrainAbstractFactory findMyTrainAbstractFactory = FindMyTrainAbstractFactory.instance();
		IFindMyTrainDAO findMyTrainDAO = findMyTrainAbstractFactory.createFindMyTrainDAO();
		IFindMyTrainLocation findMyTrainLocation = findMyTrainAbstractFactory.createFindMyTrainLocation();
		Date startDate = null;
		try {
			startDate = new SimpleDateFormat("dd-MM-yyyy").parse(startDateString);
		} catch (ParseException exception) {
			exception.printStackTrace();
		}  
		boolean hasError = false;

		/*if (user.isFirstNameValid(firstName) == true || user.isLastNameValid(lastName) == true
				|| user.isPasswordEmpty(password) || user.isConfirmPasswordEmpty(confirmPassword)) {
			model.addAttribute("nameError", true);
			hasError = true;
		}
		
		if(user.isDateValid(dateOfBirth) == false) {
			model.addAttribute("dobError", true);
			hasError = true;
		}*/
		
		if (hasError) {

			LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
			ISearchTrain searchTrain =lookupAbstractFactory.createNewSearchTrain();
			model.addAttribute(searchTrain);
			return "findMyTrain/findMyTrain";
		}
		else {
		
		ITrain train = findMyTrainDAO.getLiveTrainStatus(trainCode, startDate);
		String trainLocation = findMyTrainLocation.findMyTrainCalculation(train, startDate);
		System.out.println(trainLocation);
		model.addAttribute("trainLocation", trainLocation);
		return "findMyTrain/displayLocation";
		}
	}
	
	@RequestMapping(value = "/findMyTrain/location/done", method = RequestMethod.POST)
	public String findLocationDone(Model model) {
		System.out.println("findMyTrainLocationDone");
		return "searchTrain/searchTrain";
	}

}
