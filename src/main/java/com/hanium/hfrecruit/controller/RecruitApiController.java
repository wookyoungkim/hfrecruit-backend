package com.hanium.hfrecruit.controller;


import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.RecruitSaveRequestDto;
import com.hanium.hfrecruit.dto.RecruitUpdateRequestDto;
import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RecruitApiController {
    private final RecruitService recruitService;
    private final UserRepository userRepository;
    private final CompanyUserRepository companyUserRepository;

    @PostMapping("/api/v1/recruit")
    public Long save(@RequestBody RecruitSaveRequestDto requestDto, @SessionAttribute("user") SessionUser sessionUser) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(() -> new IllegalArgumentException("NO USER!"));
        CompanyUser loginCompanyUser = companyUserRepository.findByCompanyUserEmail(loginUser.getEmail());
        CompanyInfo loginCompany = loginCompanyUser.getCompanyInfo();
        return recruitService.save(requestDto, loginCompany, loginCompanyUser);
    }

    @PutMapping("/api/v1/recruit/update/{recruitNo}")
    public Long update(@PathVariable Long recruitNo, @RequestBody RecruitUpdateRequestDto requestDto, @SessionAttribute("user") SessionUser sessionUser) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(() -> new IllegalArgumentException("NO USER!"));
        return recruitService.update(recruitNo, requestDto);
    }

    @DeleteMapping("/api/v1/recruit/delete/{recruitNo}")
    public Long delete(@PathVariable Long recruitNo, @SessionAttribute("user") SessionUser sessionUser) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(() -> new IllegalArgumentException("NO USER!"));
        if (loginUser.getRole().equals(Role.USER)) {
            throw new IllegalArgumentException("기업 유저가 아닙니다.");
        } else if (companyUserRepository.findByCompanyUserEmail(loginUser.getEmail())
                .getRecruits()
                .stream().noneMatch(recruit -> recruit.getRecruitNo().equals(recruitNo))) {
            throw new IllegalArgumentException("작성자가 아닙니다.");
        } else {
            recruitService.delete(recruitNo);
        }
        return recruitNo;
    }
}
