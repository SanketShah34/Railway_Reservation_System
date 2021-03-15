package com.project.reservation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.project.entity.Train;

@Controller
@ComponentScan("com.project.entity")
public class ReservationController {
	
	@PostMapping("/user/bookNow")
	public String getReservationInformation(@ModelAttribute("${reservationInformation}") Reservation reservationInformation, Model model) {
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
