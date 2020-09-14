package com.hanium.hfrecruit.controller;


import com.hanium.hfrecruit.auth.dto.SessionUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
public class IndexPageController {
//    RestController 는 page를 리턴안함.
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/evaluation")
    public String ev(){
        return "evaluationPage";
    }
}
