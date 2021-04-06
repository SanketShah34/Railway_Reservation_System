package com.project.ticketCancellation;

import java.util.List;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.user.IUser;
import com.project.user.UserAbstractFactory;

@Controller
public class TicketCancellationController {
	
	private final String PNR_NUMBER = "pnrNumber";
	private final String ID_CHECKED = "idChecked";
	
	@RequestMapping(value = "/ticket/cancel")
	public String CancelTicketModel(Model model) {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUser user = userAbstractFactory.createUser();
		model.addAttribute(user); 
		return "cancelTicket/searchByPnr";
	}
	
	@PostMapping(value = "/ticket/cancellation")
	public String SearchDetailsByPnr(@RequestParam(name = PNR_NUMBER) String pnrNumber, Model model) {
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInformationDAO searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		List<IPassengerInformation> passengerInfo = searchTicketInfo.SearchPassengerInfoByPNR(pnrNumber);
		model.addAttribute("passengerInformationList", passengerInfo);
		return "cancelTicket/displayTicketInformation";
	}
	
	@PostMapping(value = "/ticket/delete")
	public String SelectTicketsToDelete(@RequestParam(name = ID_CHECKED) List<Integer> ids, Model model) {
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInformationDAO searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		ICalculateAmounts calculateAmounts = cancelTicketAbstractFactory.createNewCalculateAmounts();
		IReservation reservation = searchTicketInfo.GetAmountPaidOnTicket(ids);
		double refundedAmount = calculateAmounts.CalculateRefundAmount(reservation, ids, searchTicketInfo);
		searchTicketInfo.DeleteTickets(ids, reservation, refundedAmount);
		model.addAttribute("refundedAmount", refundedAmount);
		return "cancelTicket/cancelConfirmation";
		
	}
	
	@PostMapping(value = "/ticket/delete/done")
	public String CancelTickets(Model model) {
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInformationDAO searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		return "searchTrain/searchTrain";	
	}
}
