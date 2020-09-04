package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class MyPageController {
    @GetMapping("/mypage")
    public String mypage(){
        return "mypage";
    }

    @GetMapping("/profile")
    public String profile(){
        return "profile";
    }

    @GetMapping("/evaluate")
    public String evaluate(){
        return "evaluationPage";
    }
}
