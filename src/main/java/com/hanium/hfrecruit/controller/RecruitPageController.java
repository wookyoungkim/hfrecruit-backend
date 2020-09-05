package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.dto.RecruitSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @GetMapping("/recruit/save")
    public String recruitAdd(){
//        recruitRepository.save(dto.toEntity());
        return "recruit-add";
    }
}
