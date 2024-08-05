package com.iReserve.controller;

import com.iReserve.dto.ReservationDto;
import com.iReserve.entity.User;
import com.iReserve.service.ReservationService;
import com.iReserve.service.UserService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Calendar;

@Controller
@RequestMapping("/app")
public class ReservationController {
    private final HttpSession httpSession;
    private final ReservationService reservationService;
    private final UserService userService;

    public ReservationController(HttpSession httpSession, ReservationService reservationService,
                                 UserService userService) {
        this.httpSession = httpSession;
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping("")
    public String index(Model model) {
        updateUI(reservationService, userService, new ReservationDto(), model);
        return "app/home";
    }

    @PostMapping("/reservation/create")
    public String createReservation(@Valid ReservationDto reservationDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editReservation", false);
            updateUI(reservationService, userService, reservationDto, model);
            return "app/home";
        }
        reservationService.createReservation(reservationDto);
        updateUI(reservationService, userService, reservationDto, model);
        return "redirect:/app";
    }

    @PostMapping("/reservation/edit/{reservationId}")
    public String editReservation(@Valid ReservationDto reservationDto, BindingResult bindingResult, Model model,
                                  @PathVariable Long reservationId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editReservation", true);
            updateUI(reservationService, userService, reservationDto, model);
            return "app/home";
        }
        reservationService.updateReservation(reservationDto, reservationId);
        model.addAttribute("editReservation", false);
        updateUI(reservationService, userService, reservationDto, model);
        return "redirect:/app";
    }

    @PostMapping("/reservation/delete/{id}")
    public String deleteReservation(@PathVariable Long id, Model model) {
        reservationService.deleteReservation(id);
        updateUI(reservationService, userService, new ReservationDto(), model);
        return "redirect:/app";
    }

    public void updateUI(ReservationService reservationService, UserService userService, ReservationDto reservationDto, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("noOfUsers", userService.findAllUsers().size());
        model.addAttribute("noOfReservations", reservationService.getAllReservations().size());
        model.addAttribute("reservationDto", reservationDto);
        model.addAttribute("allReservations", reservationService.getAllReservations());
        model.addAttribute("myReservations", reservationService.getReservationById(user.getId()));
        model.addAttribute("greetings", greetings());
    }

    public String greetings() {
        Calendar calendar = Calendar.getInstance();
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        if (hour > 0 && hour < 12) {
            return "Good Morning";
        } else if (hour >= 12 && hour < 16) {
            return "Good Afternoon";
        } else {
            return "Good Evening";
        }
    }
}