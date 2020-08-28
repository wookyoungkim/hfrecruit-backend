package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationPageController {
    @GetMapping("/application")
    public String index(){

        return "index";
    }
}
