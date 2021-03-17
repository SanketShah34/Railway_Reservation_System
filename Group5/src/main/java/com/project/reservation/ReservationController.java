package com.project.reservation;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.project.setup.ITrain;

@Controller
@ComponentScan("com.project.entity")
public class ReservationController {
	
	@PostMapping("/user/bookNow")
	public String getReservationInformation(@ModelAttribute("${reservationInformation}") IReservation reservationInformation, Model model) {
		model.addAttribute("reservationInformation", reservationInformation);
		return "reservation/reservationInformation";
	}
	
	@PostMapping("/user/payNow")
	public String getPassengerInformation(@ModelAttribute("${reservationInformation}") Reservation reservationInformation, Model model) {
		reservationInformation.calculateReservationFarePerPassenger(reservationInformation);
		reservationInformation.calculateTotalReservationFare(reservationInformation);
		model.addAttribute("reservationInformation", reservationInformation);
		return "reservation/confirmAndPay";
	}
}
