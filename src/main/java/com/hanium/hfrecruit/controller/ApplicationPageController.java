package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.application.ApplicationQueryRepository;
import com.hanium.hfrecruit.domain.application.ApplicationRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.ApplicationSaveRequestDto;
import com.hanium.hfrecruit.dto.ApplicationUpdateRequestDto;
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
@RequestMapping("/application")
public class ApplicationPageController {

    @Autowired
    private final ApplicationService applicationService;
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;
    private final ApplicationRepository applicationRepository;
    private final PersonalSpecService personalSpecService;
    private final ApplicationQueryRepository applicationQueryRepository;
    private final CompanyUserRepository companyUserRepository;

    @ApiOperation(value = "지원서 리스트 전체 조회 ")
    @GetMapping("/list")
    public String applicationList(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("sideUser", loginUser);
        if(loginUser.getRole() == Role.USER){
            model.addAttribute("applications", applicationService.findAllDesc(loginUser));
            model.addAttribute("pageTitle", "내 지원서");
            return "application-list";
        }
        else{
            CompanyUser companyUser = companyUserRepository.findByCompanyUserEmail(loginUser.getEmail());
            model.addAttribute("recruits", recruitRepository.findAllByCompanyInfo(companyUser.getCompanyInfo()));
            model.addAttribute("pageTitle", "우리 회사 전체 공고");
            return "recruitlist-company";
        }
    }

    @ApiOperation(value = "내가 쓴 지원서 검색")
    @GetMapping("/search")
    public String searchRecruit(@RequestParam String keyword, Model model, @SessionAttribute("user") SessionUser sessionUser){
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        List<Application> applicationList = applicationRepository.findAllByRecruitCompanyInfoCompanyNameOrRecruitRecruitTitleContaining(sideUser.getUserNo(), keyword);
        System.out.println(applicationList);
        model.addAttribute("applications", applicationList);
        model.addAttribute("pageTitle", "' "+keyword+" '"+"로 검색한 지원서");
        return "application-list";
    }

    @ApiOperation(value = "작성중인 지원서 리스트 전체 조회 ")
    @GetMapping("/list/writing")
    public String applicationWritingList(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        List<Application> applications = applicationQueryRepository.findWritingApplication(loginUser.getUserNo());
        model.addAttribute("sideUser", loginUser);
        model.addAttribute("applications", applications);
        //여기 수정
        model.addAttribute("passStage", "평가 대기중");
        model.addAttribute("recruitClosedBit", "진행중");
        model.addAttribute("pageTitle", "작성중인 지원서");
        return "application-list-writing";
    }

    @ApiOperation(value = "진행중인지원서 리스트 전체 조회 ")
    @GetMapping("/list/active")
    public String applicationActiveList(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        List<Application> applications = applicationQueryRepository.findActiveByRecruit(loginUser.getUserNo());
        model.addAttribute("sideUser", loginUser);
        model.addAttribute("applications", applications);
        model.addAttribute("pageTitle", "진행중인 지원서");
        return "application-list-active";
    }

    @ApiOperation(value = "지원서 작성하기")
    @GetMapping("/apply/{recruitNo}")
    public String apply(@PathVariable Long recruitNo, Model model,
                        @SessionAttribute("user") SessionUser sessionUser){
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("sideUser", user);
        model.addAttribute("recruit", recruit);
        model.addAttribute("mySpecs",personalSpecService.findAllSpecByUserNo(user.getUserNo()));
        model.addAttribute("pageTitle", "지원서 작성하기");
        model.addAttribute("userProfile",user);
        return "application-apply";
    }

    @ApiOperation(value = "지원서 작성 제출")
    @PostMapping("/apply/{recruitNo}")
    @ResponseBody
    public Long save(@RequestBody ApplicationSaveRequestDto dto, @PathVariable Long recruitNo, Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        return applicationService.save(dto, loginUser, recruit);
    }

    @ApiOperation(value = "지원서 수정")
    @GetMapping("/edit/{applicationId}")
    public String edit(@PathVariable Long applicationId, Model model, @SessionAttribute("user") SessionUser sessionUser){
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        Application application = applicationRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new NoResultException("no such application"));
        Recruit recruit = application.getRecruit();
        model.addAttribute("application", application);
        model.addAttribute("recruit", recruit);
        model.addAttribute("pageTitle", "지원서 수정하기");
        return "application-edit";
    }

    @ApiOperation(value = "지원서 수정 제출")
    @PutMapping("/edit/{applicationId}")
    @ResponseBody
    public Long update(@PathVariable Long applicationId, @RequestBody ApplicationUpdateRequestDto requestDto){
        return applicationService.update(applicationId, requestDto);
    }

    @ApiOperation(value = "지원서 삭제")
    @DeleteMapping("/delete/{applicationId}")
    @ResponseBody
    public Long delete(@PathVariable Long applicationId){
        applicationService.delete(applicationId);
        return applicationId;
    }

}
