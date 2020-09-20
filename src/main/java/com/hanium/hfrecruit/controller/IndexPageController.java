package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {
//    RestController 는 page를 리턴안함.
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/evaluation")
    public String ev(Model model){
        model.addAttribute("pageTitle", "평가");
        return "evaluationPage";
    }
}
