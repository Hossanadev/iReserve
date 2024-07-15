package com.iReserve.controller;

import com.iReserve.dto.UserRequest;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Auth {
    @GetMapping("sign-up")
    public String signUp(Model model) {
        model.addAttribute("userRequest", new UserRequest());
        return "auth/sign-up";
    }

    @GetMapping("/login")
    public String login() {
        return "auth/login";
    }

    @PostMapping("/auth/sign-up")
    public String signUp(@Valid @ModelAttribute UserRequest userRequest, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userRequest", userRequest);
            return "auth/sign-up";
        }
        return "auth/login";
    }
}
