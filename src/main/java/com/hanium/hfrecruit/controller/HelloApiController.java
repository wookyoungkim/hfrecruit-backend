package com.hanium.hfrecruit.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class    HelloApiController {
    @GetMapping("/api/hello")
    public HashMap Hello(){
        HashMap<String, String> result = new HashMap<String,String>();
        result.put("message","Hi admin this is API test");
        return result;
    }
}
