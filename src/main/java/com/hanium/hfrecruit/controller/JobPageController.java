package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class JobPageController {
    @GetMapping("/job")
    public String index(){

        return "index";
    }
}
