package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.application.ApplicationQueryRepository;
import com.hanium.hfrecruit.domain.application.ApplicationRepository;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.ApplicationSaveRequestDto;
import com.hanium.hfrecruit.dto.EvaluationSaveRequestDto;
import com.hanium.hfrecruit.service.ApplicationService;
import com.hanium.hfrecruit.service.spec.PersonalSpecService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import java.util.List;

@Controller
@RequiredArgsConstructor
public class EvaluationApiController {

    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final PersonalSpecService personalSpecService;
    private final ApplicationService applicationService;

    @ApiOperation(value = "지원자 리스트 전체 조회 ")
    @GetMapping("/application/list/{recruitNo}")
    public String companyApplicationList(Model model, @PathVariable Long recruitNo, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("sideUser", loginUser);

        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        List<Application> applications = applicationRepository.findAllByRecruit(recruit);
        model.addAttribute("applications", applications);
        model.addAttribute("pageTitle", recruit.getRecruitTitle());

        return "application-list-per-recruit";
    }

    @ApiOperation(value = "지원서 평가하기")
    @GetMapping("/evaluate/{applicationId}")
    public String evaluate(@PathVariable Long applicationId, Model model, @SessionAttribute("user") SessionUser sessionUser){
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        model.addAttribute("sideUser", user);

        Application application = applicationRepository.findByApplicationId(applicationId).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        User applicant = application.getUser();
        Recruit recruit = application.getRecruit();

        model.addAttribute("pageTitle", application.getRecruit().getRecruitTitle());
        model.addAttribute("recruit", recruit);
        model.addAttribute("mySpecs",personalSpecService.findAllSpecByUserNo(user.getUserNo()));
        model.addAttribute("applicantProfile",applicant);
        model.addAttribute("application", application);
        return "evaluation";
    }

    @ApiOperation(value="평가 저장하기")
    @PutMapping("/evaluate/{applicationId}")
    @ResponseBody
    public Long saveEvaluation(@RequestBody EvaluationSaveRequestDto dto, @PathVariable Long applicationId){
        return applicationService.evaluate(applicationId, dto);
    }

}
