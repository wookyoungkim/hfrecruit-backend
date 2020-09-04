package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.PersonalSpecDto;
import com.hanium.hfrecruit.service.spec.PersonalSpecService;
import com.hanium.hfrecruit.service.spec.SpecService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@Controller
public class SpecsPageController {

    private final PersonalSpecService personalSpecService;
    private final SpecService specService;
    private final UserRepository userRepository;
    @Autowired
    public SpecsPageController(PersonalSpecService personalSpecService, SpecService specService,UserRepository userRepository) {
        this.personalSpecService = personalSpecService;
        this.specService = specService;
        this.userRepository = userRepository;
    }

    @GetMapping("/specs/{userNo}")
    public String index(@PathVariable Long userNo, Model model){
        model.addAttribute("user",userRepository.findUserByUserNo(1));
        model.addAttribute("mySpecs",personalSpecService.findAllSpecByUserNo(userNo));
//        model.addAttribute("specNames",specService.findAllBySpecId(personalSpecService.findAllSpecByUserNo(userNo)));
        model.addAttribute("specs",specService.findAll());
        return "myspec";
    }
    @ResponseBody
    @PostMapping("/specs/save/{userNo}")
    public Long save(@PathVariable Long userNo, @RequestBody HashMap<String,Object> params) {
        PersonalSpecDto personalSpecDto = personalSpecService.createWithParams(params);
        personalSpecDto.setUser(userRepository.findUserByUserNo(userNo));
        personalSpecDto.setSpec(specService.findBySpecId(Long.valueOf( (String)params.get("specId"))).toEntity());
        return personalSpecService.save(personalSpecDto);
    }
}
