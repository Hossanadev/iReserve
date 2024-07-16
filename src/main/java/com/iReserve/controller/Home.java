package com.iReserve.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/app")
public class Home {

    @GetMapping("/index")
    public String index() {
        return "app/home";
    }
}