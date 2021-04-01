package com.project.setup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CancelTrainController {

	@ModelAttribute("cancelTrain")
    public ICancelTrain getIRouteModelObject(HttpServletRequest request) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		return cancelTrain;
	}
	
	@GetMapping(value = "/admin/cancelTrain")
	public String displayCancelTrain(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		model.addAttribute("cancelTrain", cancelTrain);
		return "cancelTrain/cancelTrain";
	}
	
	@PostMapping(value = "/admin/cancelTrain/success")
	public String cancelTrain(@ModelAttribute("cancelTrain") ICancelTrain cancelTrain, Model model) {
		String errorMessage = cancelTrain.validateTrainDate();
		if (errorMessage.equals("")) {
			return "home";
		} else {
			model.addAttribute("cancelTrain", cancelTrain);
			return "cancelTrain/cancelTrain";
		}
	}

}
