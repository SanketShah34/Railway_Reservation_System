package com.project.findMyTrain;


import java.util.Date;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.lookup.ISearchTrain;
import com.project.lookup.LookupAbstractFactory;
import com.project.user.IUser;
import com.project.user.UserAbstractFactory;
import com.project.user.UserConcreteFactory;

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
								@RequestParam(name = START_DATE) Date startDate,
								Model model) {
		FindMyTrainAbstractFactory findMyTrainAbstractFactory = FindMyTrainAbstractFactory.instance();
		IFindMyTrainDAO findMyTrainDAO = findMyTrainAbstractFactory.createFindMyTrainDAO();
		findMyTrainDAO.getLiveTrainStatus(trainCode, startDate);
		System.out.println("findMyTrainLocation");
		return "findMyTrain/displayLocation";
	}
	
	@RequestMapping(value = "/findMyTrain/location/done", method = RequestMethod.POST)
	public String findLocationDone(Model model) {
		System.out.println("findMyTrainLocationDone");
		return "searchTrain/searchTrain";
	}

}
