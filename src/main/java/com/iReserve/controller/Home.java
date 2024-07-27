package com.iReserve.controller;

import com.iReserve.entity.User;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class Home {
    private final HttpSession httpSession;

    public Home(HttpSession httpSession) {
        this.httpSession = httpSession;
    }

    @GetMapping("")
    public String index(Model model) {
        User user = (User) httpSession.getAttribute("user");
        model.addAttribute("user", user);
        return "app/home";
    }
}