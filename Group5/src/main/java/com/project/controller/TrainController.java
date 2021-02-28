package com.project.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.project.entity.Station;
import com.project.entity.Train;
import com.project.service.StationService;
import com.project.service.TrainService;

@Controller
@ComponentScan("com.project.entity")
public class TrainController {

	@Autowired
	TrainService trainService;
	
	@Autowired
	StationService stationService;
	
	@GetMapping(value = "/train/list")
	public String ShowTrainPage(Model model) {
		List<Train> listOfTrain = trainService.ListOfTrains();
		System.out.println("PPPP" + listOfTrain.size());
		model.addAttribute("listOfTrain", listOfTrain);
		return "train/train";
	}

	@GetMapping(value = "/train")
	public String ShowTrain(Model model) {
		List<Train> listOfTrain = trainService.ListOfTrains();
		System.out.println("PPPP" + listOfTrain.size());
		model.addAttribute("listOfTrain", listOfTrain);
		return "train/train";
	}

	@GetMapping(value = "/train/add")
	public String ShowAddTrainPage(Model model) {
		List<Station> stations = stationService.ListOfStations();
		model.addAttribute("listOfStations", stations);

		Train train = new Train();
		model.addAttribute(train);
		return "train/add_train";
	}

	@PostMapping(value = "/train/save")
	public String saveTrain(@Valid @ModelAttribute("train") Train train,BindingResult result) {

		if (result.hasErrors()) {
			System.out.println("in if");
			return "train/add_train";
		} else {
			System.out.println("in else");
			trainService.saveTrain(train);
			return "redirect:/train/list";
		}

	}

	@RequestMapping("/train/edit/{trainId}")
	public String showEditTrainPage(@PathVariable(name = "trainId") Integer trainId, Model model) {

		List<Station> stations = stationService.ListOfStations();
		model.addAttribute("listOfStations", stations);
		
		Train train = trainService.getTrain(trainId);
		model.addAttribute(train);
		
		String[] daysList = train.getDays().split(",");
		Map<String, String> allDays = new HashMap();
		for(String day: daysList) {
			allDays.put(day, "true");
		}
		model.addAttribute("listOfDays",allDays);
		return "train/edit_train";
	}

	@RequestMapping("/train/delete/{trainId}")
	public String deleteTrain(@PathVariable(name = "trainId") Integer trainId, Model model) {
		trainService.deleteTrain(trainId);
		return "redirect:/train/list";

	}
	
}
