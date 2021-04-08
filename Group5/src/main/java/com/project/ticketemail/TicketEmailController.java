package com.project.ticketemail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketEmailController {

	@GetMapping(value = "/user/ticketemail")
	public String downloadTicketPDF(@RequestParam(name ="reservationId") int reservationId,Model model) {
		TicketEmailAbstractFactory ticketEmailAbstractFactory = TicketEmailAbstractFactory.instance();
		ITicketEmailDAO ticketEmailDAO = ticketEmailAbstractFactory.createNewTicketEmailDAO();
		ITicketEmail ticketEmail = ticketEmailDAO.ticketEmail(reservationId);
		model.addAttribute("ticketEmail", ticketEmail);
		return "redirect:/user/home";
	}
}