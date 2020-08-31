package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SpecsPageController {
    @GetMapping("/specs")
    public String index(){

        return "myspec";
    }
}
