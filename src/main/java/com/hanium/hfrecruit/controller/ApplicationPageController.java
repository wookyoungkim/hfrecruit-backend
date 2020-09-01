package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationPageController {
    @GetMapping("/application")
    public String application(){

        return "application";
    }
    @GetMapping("/apply")
    public String apply(){

        return "apply";
    }
}
