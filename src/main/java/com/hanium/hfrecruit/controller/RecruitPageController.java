package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class RecruitPageController {

    private final RecruitService recruitService;

    @GetMapping("/recruit")
    public String recruit(Model model){
        model.addAttribute("recruit", recruitService.findAllDesc());
        return "recruit";
    }

    @GetMapping("/recruit/{recruitNo}")
    public String recruitDetail(@PathVariable Long recruitNo, Model model){
        model.addAttribute("recruit", recruitService.findOne(recruitNo));
        return "recruit-detail";
    }

    @GetMapping("/recruit/save")
    public String recruitAdd(){
//        recruitRepository.save(dto.toEntity());
        return "recruit-add";
    }
}
