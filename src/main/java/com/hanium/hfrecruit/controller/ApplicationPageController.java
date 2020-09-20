package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.application.ApplicationQueryRepository;
import com.hanium.hfrecruit.domain.application.ApplicationRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
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
import javax.servlet.http.HttpSession;
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

    @ApiOperation(value = "지원서 리스트 전체 조회 ")
    @GetMapping("/list")
    public String applicationList(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("sideUser", loginUser);
        model.addAttribute("applications", applicationService.findAllDesc(loginUser));
        model.addAttribute("pageTitle", "내 지원서");
        return "applicationlist";
    }
    @ApiOperation(value = "지원서 리스트 전체 조회 ")
    @GetMapping("/list/writing")
    public String applicationWritingList(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        List<Application> applications = applicationQueryRepository.findWritingApplication(loginUser.getUserNo());
        model.addAttribute("sideUser", loginUser);
        model.addAttribute("applications", applications);
        model.addAttribute("pageTitle", "작성중인 지원서");
        return "application-list-writing";
    }
    @ApiOperation(value = "지원서 리스트 전체 조회 ")
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

    @ApiOperation(value = "지원서 작성")
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
        return "apply";
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
        return "editApplication";
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
