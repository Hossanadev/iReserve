package com.iReserve.controller;

import com.iReserve.dto.UserDto;
import com.iReserve.entity.User;
import com.iReserve.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class AuthController {
    private final HttpSession httpSession;
    private final UserServiceImpl userServiceImpl;

    @Autowired
    public AuthController(UserServiceImpl userServiceImpl, HttpSession httpSession) {
        this.userServiceImpl = userServiceImpl;
        this.httpSession = httpSession;
    }

    @GetMapping("/")
    public String index(Model model) {
        User user = (User) httpSession.getAttribute("user");
        if (user != null) {
            model.addAttribute("hasActiveSession", true);
        } else {
            model.addAttribute("hasActiveSession", false);
        }
        model.addAttribute("userDto", new UserDto());
        return "index";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "index";
        }
        String message = userServiceImpl.createUser(userDto);
        model.addAttribute("message", message);
        return "index";
    }
}