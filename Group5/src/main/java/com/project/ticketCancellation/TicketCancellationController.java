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
import com.project.user.IUser;
import com.project.user.UserAbstractFactory;

@Controller
public class TicketCancellationController {
	
	private final String PNR_NUMBER = "pnrNumber";
	
	@RequestMapping(value = "/ticket/cancel")
	public String CancelTicketModel(Model model) {
		System.out.println("Cancel Ticket Page");
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUser user = userAbstractFactory.createUser();
		model.addAttribute(user); 
		return "cancelTicket/searchByPnr";
	}
	
	@PostMapping(value = "/ticket/cancellation")
	public String SearchDetailsByPnr(@RequestParam(name = PNR_NUMBER) String pnrNumber, Model model, Authentication authentication) {
		String userName = authentication.getName();
		System.out.println("Pnr page");
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInfo searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		List<IPassengerInformation> passengerInfo = searchTicketInfo.SearchPassengerInfoByPNR(userName, pnrNumber);
		model.addAttribute("passengerInformationList", passengerInfo);
		return "cancelTicket/displayTicketInformation";
	}
	
	@PostMapping(value = "/ticket/delete")
	public String SelectTicketsToDelete(@RequestParam("idChecked") List<Integer> ids, Model model)  {
		System.out.println("Delete page");
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInfo searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		double amountPaid = searchTicketInfo.getAmountPaidOnTicket(ids);
		double refundedAmount = searchTicketInfo.calculateRefundAmount(amountPaid);
		model.addAttribute("refundedAmount", refundedAmount);
		return "cancelTicket/cancelConfirmation";
		
	}
	
	@PostMapping(value = "/ticket/delete/confirm")
	public String ConfirmCancelTickets(@ModelAttribute("passengerInformationList") List<IPassengerInformation> passengerInfo,
										Model model) {
		System.out.println("User wants to delete tickets");
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInfo searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		System.out.println("deleted tickets");
		return "searchTrain/searchTrain";
		
	}
	
	@PostMapping(value = "/ticket/delete/cancel")
	public String CancelTickets(@ModelAttribute("passengerInformationList") List<IPassengerInformation> passengerInfo,
										Model model) {
		System.out.println("User doesn't wants to delete tickets");
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInfo searchTicketInfo = cancelTicketAbstractFactory.createNewSearchPassengerInfo();
		
		return "searchTrain/searchTrain";
		
	}
}
