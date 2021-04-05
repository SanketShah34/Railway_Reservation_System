package com.project.setup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.calculation.CalculationAbstractFactory;
import com.project.calculation.IAvailableSeats;
import com.project.calculation.ISeatAvailibilityDAO;
import com.project.calculation.ITrainFilterAndCalculation;
import com.project.cancelTrain.CancelTrainAbstractFactory;
import com.project.cancelTrain.ITrainCancellation;
import com.project.cancelTrain.ITrainCancellationDAO;
import com.project.lookup.ISearchTrain;
import com.project.lookup.ISearchTrainDAO;
import com.project.lookup.LookupAbstractFactory;
import com.project.reservation.IReservation;

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
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		CalculationAbstractFactory calculationAbstractFactory = CalculationAbstractFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		CancelTrainAbstractFactory cancelTrainAbstractFactory = CancelTrainAbstractFactory.instance();
		ISearchTrainDAO searchTrainDAO = lookupAbstractFactory.createSearchTrainDAO();
		IRouteDAO routeDAO = setupAbstractFactory.createNewRouteDAO();
		ISeatAvailibilityDAO seatAvailabilityDAO = calculationAbstractFactory.createNewSeatAvailibilityDAO();
		ITrainCancellationDAO trainCancellationDAO = cancelTrainAbstractFactory.createNewTrainCancellationDAO();
		ITrainCancellation trainCancellation = cancelTrainAbstractFactory.createNewTrainCancellation();
		List<IReservation> reservationList = trainCancellationDAO.fetchAllReservations(cancelTrain);
		trainCancellation.cancelOrRescheduleTicket(reservationList, searchTrainDAO, routeDAO, seatAvailabilityDAO);
		return "home";
	}

}
