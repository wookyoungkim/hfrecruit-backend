package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class RecruitPageController {

    private final RecruitService recruitService;

    @GetMapping("/recruit")
    public String recruit(Model model){
        model.addAttribute("recruit", recruitService.findAllDesc());
        return "recruit";
    }

    @GetMapping("/recruit-detail")
    public String recruitDetail(){
        return "recruit-detail";
    }

    @GetMapping("/recruit/save")
    public String recruitAdd(){
//        recruitRepository.save(dto.toEntity());
        return "recruit-add";
    }
}
