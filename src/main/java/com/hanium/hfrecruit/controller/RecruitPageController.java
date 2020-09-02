package com.hanium.hfrecruit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RecruitPageController {
    @GetMapping("/recruit")
    public String recruit(){
        return "recruit";
    }
    @GetMapping("/recruit-detail")
    public String recruitDetail(){
        return "recruit-detail";
    }
    @GetMapping("/recruit-add")
    public String recruitAdd(){
        return "recruit-add";
    }
}
