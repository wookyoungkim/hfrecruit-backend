package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.application.ApplicationQueryRepository;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.UserUpdateRequestDto;
import com.hanium.hfrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MyPageController {

    private final UserRepository userRepository;
    private final ApplicationQueryRepository applicationQueryRepository;
    private final RecruitRepository recruitRepository;
    private final CompanyUserRepository companyUserRepository;
    private final CompanyInfoRepository companyInfoRepository;
    private final UserService userService;

    @GetMapping("/mypage")
    public String mypage(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );

        model.addAttribute("pageTitle", "MY PAGE");
        model.addAttribute("user", user);
        model.addAttribute("sideUser", user);

        if(user.getRole()== Role.COMPANYUSER) {
            model.addAttribute("company", companyInfoRepository.findByCompanyNo(companyUserRepository.findByCompanyUserEmail(user.getEmail()).getCompanyInfo().getCompanyNo()));
            if(companyUserRepository.findByCompanyUserEmail(user.getEmail()).getCompanyUserCode().equals(1)) {
                model.addAttribute("companyUserCode", "채용관리자");
            } else{
                model.addAttribute("companyUserCode", "인사담당자");
            }
            model.addAttribute("companyUserId", companyUserRepository.findByCompanyUserEmail(user.getEmail()).getCompanyUserId());
            model.addAttribute("role", "회원");
            model.addAttribute("checker", "1");
            List<Recruit> allRecruits = recruitRepository.findAllByCompanyInfo(companyInfoRepository.findByCompanyNo(companyUserRepository.findByCompanyUserEmail(user.getEmail()).getCompanyInfo().getCompanyNo()));
            model.addAttribute("recruits", allRecruits);
            model.addAttribute("allRecruits", (long) allRecruits.size());
            model.addAttribute("activeRecruits", allRecruits.stream().filter(recruit -> recruit.getClosedBit()==null).count());
            model.addAttribute("doneRecruits", allRecruits.stream().filter(recruit -> recruit.getClosedBit()==1).count());
        }else {
            model.addAttribute("role", "일반회원");
            model.addAttribute("checker", null);
            Integer active = applicationQueryRepository.findActiveByRecruit(user.getUserNo()).size();
            Integer writing = applicationQueryRepository.findWritingApplication(user.getUserNo()).size();
            model.addAttribute("activeApplications", active);
            model.addAttribute("writingApplications", writing);
        }
        return "mypage";
    }

    @GetMapping("/profile")
    public String profile(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        model.addAttribute("user", user);
        model.addAttribute("sideUser", user);
        model.addAttribute("pageTitle", "개인정보수정");
        return "profile";
    }

    @PutMapping("/mypage/infoUpdate")
    @ResponseBody
    public Long update(@SessionAttribute("user") SessionUser sessionUser, @RequestBody UserUpdateRequestDto requestDto){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        return userService.update(loginUser.getUserNo(), requestDto);
    }

    @GetMapping("/evaluate")
    public String evaluate(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);
        return "evaluation";
    }
}
