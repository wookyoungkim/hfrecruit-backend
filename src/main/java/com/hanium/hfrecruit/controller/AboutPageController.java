package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutPageController {
    @GetMapping("/about")
    public String About(Model model){
        model.addAttribute("pageTitle","About");
        return "about";
    }
}
