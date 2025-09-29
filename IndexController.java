package com.sistek.sos.analysis_dashboard.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String examplePage(Model model) {
        model.addAttribute("message", "Home Page of Analysis Dashboard");
        return "index";
    }
}


