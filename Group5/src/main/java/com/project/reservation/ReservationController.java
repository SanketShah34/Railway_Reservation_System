package com.project.reservation;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {
	
	@ModelAttribute("reservationInformation")
    public IReservation getIReservationModelObject(HttpServletRequest request) {
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		IReservation reservation = reservationAbstractFactory.createReservation();
		return reservation ;
	}
	
	@PostMapping("/user/bookNow")
	public String getReservationInformation(@ModelAttribute("reservationInformation") IReservation reservationInformation, Model model) {
		model.addAttribute("reservationInformation", reservationInformation);
		return "reservation/reservationInformation";
	}
	
	@PostMapping("/user/payNow")
	public String getPassengerInformation(@ModelAttribute("reservationInformation") IReservation reservationInformation, Model model) {
		reservationInformation.calculateTotalReservationFare(reservationInformation);
		model.addAttribute("reservationInformation", reservationInformation);
		return "reservation/confirmAndPay";
	}
}
