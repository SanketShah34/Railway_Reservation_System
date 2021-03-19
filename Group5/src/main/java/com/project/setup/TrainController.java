package com.project.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrainController {
	
	@GetMapping(value = "/admin/train/list")
	public String showTrainPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		List<Train> listOfTrain = trainDAO.getAllTrain();
		model.addAttribute("listOfTrain", listOfTrain);
		return "train/train";
	}

	@GetMapping(value = "/admin/train")
	public String showTrain(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		List<Train> listOfTrain = trainDAO.getAllTrain();
		model.addAttribute("listOfTrain", listOfTrain);
		return "train/train";
	}

	@GetMapping(value = "/admin/train/add")
	public String showAddTrainPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> stations = stationDAO.getAllStation();
		ITrain train = setupAbstractFactory.createTrain();
		model.addAttribute("listOfStations", stations);
		model.addAttribute(train);
		return "train/add_train";
	}

	@PostMapping(value = "/admin/train/save")
	public String saveTrain(@Valid @ModelAttribute("train") ITrain train,BindingResult result) {

		if (result.hasErrors()) {
			return "train/add_train";
		} else {
			SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
			ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
			if(trainDAO.saveTrain(train)) {
				return "redirect:/admin/train/list";
			}
			else {
				return "redirect:/admin/train-route-error";
			}
		}
	}

	@RequestMapping("/admin/train/edit/{trainId}")
	public String showEditTrainPage(@PathVariable(name = "trainId") Integer trainId, Model model) {

		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> stations = stationDAO.getAllStation();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		model.addAttribute("listOfStations", stations);
		
		ITrain train = trainDAO.getTrain(trainId);
		model.addAttribute(train);
		
		String[] daysList = train.getDays().split(",");
		Map<String, String> allDays = new HashMap<>();
		for(String day: daysList) {
			allDays.put(day, "true");
		}
		
		String[] middleStationsList = train.getMiddleStations().split(",");
		model.addAttribute("listOfMiddleStations", middleStationsList);
		
		model.addAttribute("listOfDays",allDays);
		return "train/edit_train";
	}

	@RequestMapping("/admin/train/delete/{trainId}")
	public String deleteTrain(@PathVariable(name = "trainId") Integer trainId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		trainDAO.deleteTrain(trainId);
		return "redirect:/admin/train/list";

	}
	
	@RequestMapping("/admin/train-route-error")
	public String trainRouteError(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		model.addAttribute("trainRouteError", true);
		List<Train> listOfTrain = trainDAO.getAllTrain();
		model.addAttribute("listOfTrain", listOfTrain);
		return "train/train";
	}
}
