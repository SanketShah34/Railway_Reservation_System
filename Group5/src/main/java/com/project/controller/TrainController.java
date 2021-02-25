package com.project.controller;

import java.util.List;

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

import com.project.entity.Train;
import com.project.service.TrainService;

@Controller
@ComponentScan("com.project.entity")
public class TrainController {

	@Autowired
	TrainService trainService;
	
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

	@RequestMapping("/train/edit/{sid}")
	public String showEditTrainPage(@PathVariable(name = "sid") Integer sid, Model model) {

		Train train = trainService.getTrain(sid);
		model.addAttribute(train);
		return "train/edit_train";
	}

	@RequestMapping("/train/delete/{sid}")
	public String deleteTrain(@PathVariable(name = "sid") Integer sid, Model model) {
		trainService.deleteTrain(sid);
		return "redirect:/train/list";

	}
	
}
