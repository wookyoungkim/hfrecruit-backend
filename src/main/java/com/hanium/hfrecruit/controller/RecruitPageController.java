package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.dto.RecruitResponseDto;
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
    private final CompanyInfoRepository companyInfoRepository;
    private final RecruitRepository recruitRepository;

    @GetMapping("/recruit")
    public String recruit(Model model){
        model.addAttribute("recruit", recruitService.findAll());

        CompanyUser companyUser = companyUserRepository.getOne((long) 1);   //이거 로그인 유저 되면 바꿔야함
        CompanyInfo companyInfo = companyInfoRepository.findByCompanyNo((long) 1);  //임시로 넣어둔 것!!
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("companyUser", companyUser);
        model.addAttribute("pageTitle", "공고리스트보기");
        return "recruit";
    }

    @GetMapping("/recruit/{recruitNo}")
    public String recruitDetail(@PathVariable Long recruitNo, Model model){
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("recruit", recruitService.findOne(recruitNo));

        CompanyUser companyUser = companyUserRepository.getOne((long) 1);   //이거 로그인 유저 되면 바꿔야함
        CompanyInfo companyInfo = companyInfoRepository.findByCompanyNo((long) 1);  //임시로 넣어둔 것!!
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("companyUser", companyUser);
        model.addAttribute("pageTitle", recruit.getRecruitTitle());
        return "recruit-detail";
    }

    @GetMapping("/recruit/save")
    public String recruitAdd(){
        return "recruit-add";
    }

    @GetMapping("/recruit/update/{id}")
    public String recruitUpdate(@PathVariable Long id, Model model){
        RecruitResponseDto dto = recruitService.findById(id);
        model.addAttribute("recruit", dto);

        return "recruit-update";
    }
}
