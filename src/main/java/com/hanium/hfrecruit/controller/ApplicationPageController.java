package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.User;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@Controller
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationPageController {

    private final RecruitRepository recruitRepository;

    @ApiOperation(value = "지원서 리스트 전체 조회 ")
    @GetMapping("/list")
    public String applicationList(){
        return "application";
    }

    @GetMapping("/apply/{recruitNo}")
    public String apply(@PathVariable Long recruitNo, Model model){
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("erroror"));
        model.addAttribute("recruit", recruit);

        return "apply";
    }


//    @ApiOperation(value = "지원서 작성")
//    @PostMapping("/{recruitNo}/{applicationId}")
//    public Long save(@RequestBody ApplicationSaveRequestDto dto){
//        return applicationService.save(dto);
//
//    }
}
