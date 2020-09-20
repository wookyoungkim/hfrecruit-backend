package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.user.User;
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

    @GetMapping("/specs")
    public String index(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        model.addAttribute("pageTitle","내 스펙");
        model.addAttribute("user",user);
        model.addAttribute("mySpecs",personalSpecService.findAllSpecByUserNo(user.getUserNo()));
        model.addAttribute("specs",specService.findAll());
        return "myspec";
    }
    @ResponseBody
    @PostMapping("/specs/save")
    public Long save(@SessionAttribute("user") SessionUser sessionUser, @RequestBody HashMap<String,Object> params) {
        PersonalSpecDto personalSpecDto = personalSpecService.createWithParams(params);
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        personalSpecDto.setUser(userRepository.findUserByUserNo(user.getUserNo()));
        personalSpecDto.setSpec(specService.findBySpecId(Long.valueOf( (String)params.get("specId"))).toEntity());
        return personalSpecService.save(personalSpecDto);
    }
    @ResponseBody
    @DeleteMapping("/specs/delete/{personalSpecId}")
    public Long delete(@PathVariable Long personalSpecId) {
        personalSpecService.delete(personalSpecId);
        return personalSpecId;
    }
}
