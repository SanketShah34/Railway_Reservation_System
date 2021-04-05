package com.project.findMyTrain;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.lookup.ISearchTrain;
import com.project.lookup.LookupAbstractFactory;

public class FindMyTrainController {
	
	private final String TRAIN_CODE = "trainCode";

	@RequestMapping(value = "/findMyTrain")
	public String findMyTrain(Model model) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ISearchTrain searchTrain =lookupAbstractFactory.createNewSearchTrain();
		System.out.println("findMyTrain");
		model.addAttribute(searchTrain);
		return "findMyTrain/findMyTrain";
	}
	
	@RequestMapping(value = "/findMyTrain/location", method = RequestMethod.POST)
	public String findLocation(@RequestParam(name = TRAIN_CODE) String trainCode,
								Model model) {
		System.out.println("findMyTrainLocation");
		return trainCode;
	}
	
	@RequestMapping(value = "/findMyTrain/location/done", method = RequestMethod.POST)
	public String findLocationDone(Model model) {
		System.out.println("findMyTrainLocationDone");
		return "searchTrain/searchTrain";
	}

}
