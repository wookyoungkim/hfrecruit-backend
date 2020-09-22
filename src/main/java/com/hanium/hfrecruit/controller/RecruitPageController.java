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
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
    public String recruit(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);
        LocalDateTime current = LocalDateTime.now();
        List<Recruit> closedRecruits = recruitRepository.findAll()
                .stream()
                .filter(recruit -> recruit.getClosedDate().before(Timestamp.valueOf(current)))
                .collect(Collectors.toList());
        for(Recruit recruit: closedRecruits){
            recruitService.updateBit(recruit.getRecruitNo());
        }
        model.addAttribute("recruit", recruitRepository.findAll());
        model.addAttribute("pageTitle", "전체 채용 공고");
        return "recruit";
    }

    @GetMapping("/recruit/{recruitNo}")
    public String recruitDetail(@PathVariable Long recruitNo, Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("recruit", recruit);
        model.addAttribute("pageTitle", recruit.getRecruitTitle());
        return "recruit-detail";
    }

    @GetMapping("/recruit/save")
    public String recruitAdd(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new IllegalArgumentException("NO USER!"));
        if(loginUser.getRole().equals(Role.USER)){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        model.addAttribute("pageTitle", "채용 공고 작성하기");
        return "recruit-add";
    }

    @GetMapping("/recruit/update/{id}")
    public String recruitUpdate(@PathVariable Long id, Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new IllegalArgumentException("NO USER!"));
        RecruitResponseDto recruitResponseDto = recruitService.findById(id);
        if(companyUserRepository.findByCompanyUserEmail(loginUser.getEmail())
                .getRecruits()
                .stream().noneMatch(recruit -> recruit.getRecruitNo().equals(recruitResponseDto.getRecruitNo()))){
            throw new IllegalArgumentException("권한이 없습니다.");
        }
        model.addAttribute("sideUser", loginUser);
        model.addAttribute("startDate", recruitResponseDto.getStartDate().toLocalDateTime());
        model.addAttribute("closeDate", recruitResponseDto.getClosedDate().toLocalDateTime());
        model.addAttribute("recruit", recruitResponseDto);
        model.addAttribute("pageTitle", recruitResponseDto.getRecruitTitle()+" 수정");
        return "recruit-update";
    }

    @GetMapping("/recruit/search")
    public String searchRecruit(@RequestParam String keyword, Model model, @SessionAttribute("user") SessionUser sessionUser){
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        List<Recruit> recruitList = recruitRepository.findAllByCompanyInfoCompanyNameOrRecruitContentOrRecruitTitleContaining(keyword);
        System.out.println(recruitList);
        model.addAttribute("recruit", recruitList);
        model.addAttribute("pageTitle", "' "+keyword+" '"+"로 검색한 공고");
        return "recruit";
    }
}
