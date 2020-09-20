package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.RecruitResponseDto;
import com.hanium.hfrecruit.service.CompanyUserService;
import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;

@RequiredArgsConstructor
@Controller
public class RecruitPageController {
    private final RecruitService recruitService;
    private final CompanyUserRepository companyUserRepository;
    private final CompanyUserService companyUserService;
    private final CompanyInfoRepository companyInfoRepository;
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;


    @GetMapping("/recruit")
    public String recruit(Model model, @SessionAttribute("user") SessionUser sessionUser){
        model.addAttribute("recruit", recruitRepository.findAll());
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                   .orElseThrow(() -> new NoResultException("No user!"));
        model.addAttribute("pageTitle", "전체 채용 공고");
        return "recruit";
    }

    @GetMapping("/recruit/{recruitNo}")
    public String recruitDetail(@PathVariable Long recruitNo, Model model){
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("recruit", recruit);
        model.addAttribute("pageTitle", recruit.getRecruitTitle());
        return "recruit-detail";
    }

    @GetMapping("/recruit/save")
    public String recruitAdd(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new IllegalArgumentException("NO USER!"));
        model.addAttribute("pageTitle", "채용 공고 작성하기");
        return "recruit-add";
    }

    @GetMapping("/recruit/update/{id}")
    public String recruitUpdate(@PathVariable Long id, Model model){
        RecruitResponseDto dto = recruitService.findById(id);
        model.addAttribute("recruit", dto);
        model.addAttribute("pageTitle", dto.getRecruitTitle()+" 수정");
        return "recruit-update";
    }
}
