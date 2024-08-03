package com.iReserve.controller;

import com.iReserve.dto.ReservationDto;
import com.iReserve.entity.User;
import com.iReserve.service.ReservationServiceImpl;
import com.iReserve.service.UserServiceImpl;
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
    private final ReservationServiceImpl reservationServiceImpl;
    private final UserServiceImpl userServiceImpl;

    public ReservationController(HttpSession httpSession, ReservationServiceImpl reservationServiceImpl,
                                 UserServiceImpl userServiceImpl) {
        this.httpSession = httpSession;
        this.reservationServiceImpl = reservationServiceImpl;
        this.userServiceImpl = userServiceImpl;
    }

    @GetMapping("")
    public String index(Model model) {
        updateUI(reservationServiceImpl, userServiceImpl, new ReservationDto(), model);
        return "app/home";
    }

    @PostMapping("/reservation/create")
    public String createReservation(@Valid ReservationDto reservationDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editReservation", false);
            updateUI(reservationServiceImpl, userServiceImpl, reservationDto, model);
            return "app/home";
        }
        reservationServiceImpl.createReservation(reservationDto);
        updateUI(reservationServiceImpl, userServiceImpl, reservationDto, model);
        return "redirect:/app";
    }

    @PostMapping("/reservation/edit/{reservationId}")
    public String editReservation(@Valid ReservationDto reservationDto, BindingResult bindingResult, Model model,
                                  @PathVariable Long reservationId) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("editReservation", true);
            updateUI(reservationServiceImpl, userServiceImpl, reservationDto, model);
            return "app/home";
        }
        reservationServiceImpl.updateReservation(reservationDto, reservationId);
        model.addAttribute("editReservation", false);
        updateUI(reservationServiceImpl, userServiceImpl, reservationDto, model);
        return "redirect:/app";
    }

    @PostMapping("/reservation/delete/{id}")
    public String deleteReservation(@PathVariable Long id, Model model) {
        reservationServiceImpl.deleteReservation(id);
        updateUI(reservationServiceImpl, userServiceImpl, new ReservationDto(), model);
        return "redirect:/app";
    }

    public void updateUI(ReservationServiceImpl reservationServiceImpl, UserServiceImpl userServiceImpl, ReservationDto reservationDto, Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        model.addAttribute("noOfUsers", userServiceImpl.findAllUsers().size());
        model.addAttribute("noOfReservations", reservationServiceImpl.getAllReservations().size());
        model.addAttribute("reservationDto", reservationDto);
        model.addAttribute("allReservations", reservationServiceImpl.getAllReservations());
        model.addAttribute("myReservations", reservationServiceImpl.getReservationById(user.getId()));
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