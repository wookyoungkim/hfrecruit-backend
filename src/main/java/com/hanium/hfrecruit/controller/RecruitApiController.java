package com.hanium.hfrecruit.controller;


import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.ApplicationSaveRequestDto;
import com.hanium.hfrecruit.dto.RecruitSaveRequestDto;
import com.hanium.hfrecruit.dto.RecruitUpdateRequestDto;
import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@RestController
public class RecruitApiController {
    private final RecruitService recruitService;
    private final UserRepository userRepository;
    private final CompanyUserRepository companyUserRepository;
    private final CompanyInfoRepository companyInfoRepository;
/*
    public Long save(@RequestBody ApplicationSaveRequestDto dto, @PathVariable Long recruitNo, HttpSession session){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));

        return applicationService.save(dto, loginUser, recruit);*/

    @PostMapping("/api/v1/recruit")
    public Long save(@RequestBody RecruitSaveRequestDto requestDto, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new IllegalArgumentException("NO USER!"));
        CompanyUser loginCompanyUser = companyUserRepository.getOne(loginUser.getUserNo());
        CompanyInfo loginCompany = loginCompanyUser.getCompanyInfo();
        return recruitService.save(requestDto, loginCompany, loginCompanyUser);
    }

    @PutMapping("/api/v1/recruit/update/{recruitNo}")
    public Long update(@PathVariable Long recruitNo, @RequestBody RecruitUpdateRequestDto requestDto){
        return recruitService.update(recruitNo, requestDto);
    }

    @DeleteMapping("/api/v1/recruit/delete/{recruitNo}")
    public Long delete(@PathVariable Long recruitNo){
        recruitService.delete(recruitNo);
        return recruitNo;
    }
}
