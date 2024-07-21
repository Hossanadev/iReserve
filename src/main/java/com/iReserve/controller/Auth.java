package com.iReserve.controller;

import com.iReserve.dto.UserDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class Auth {
    @GetMapping("/sign-up")
    public String signUp(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/sign-up";
    }

    @GetMapping("/login")
    public String login(Model model) {
        model.addAttribute("userDto", new UserDto());
        return "auth/login";
    }

    @PostMapping("/sign-up")
    public String signUp(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/sign-up";
        }
        return "auth/login";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute UserDto userDto, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("userDto", userDto);
            return "auth/login";
        }
        return "redirect:/app/index";
    }
}