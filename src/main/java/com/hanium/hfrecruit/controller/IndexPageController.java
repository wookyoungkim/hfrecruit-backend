package com.hanium.hfrecruit.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexPageController {
//    RestController 는 page를 리턴안함.
    @GetMapping("/")
    public String index(){

        return "index";
    }
}
