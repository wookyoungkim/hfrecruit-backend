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
        // 세션에서 받아온 유저정보를 통해 가입된 유저가 맞는지 검사한다.
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        // 맞으면 화면 내의 side bar에 띄워주기 위해 로그인된 유저 정보를 model에 추가한다.
        model.addAttribute("sideUser", loginUser);
        // 일반 유저의 경우 지원서를
        if(loginUser.getRole() == Role.USER){
            model.addAttribute("applications", applicationService.findAllDesc(loginUser));
            model.addAttribute("pageTitle", "내 지원서");
            return "application-list";
        }
        // 기업 유저의 경우 지원서를 띄운다.
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

        // 해당 유저의 정보를 포함하는 지원서를 검색한다.
        List<Application> applicationList = applicationRepository.findAllByRecruitCompanyInfoCompanyNameOrRecruitRecruitTitleContaining(sideUser.getUserNo(), keyword);
        // System.out.println(applicationList);
        model.addAttribute("applications", applicationList);
        model.addAttribute("pageTitle", "' "+keyword+" '"+"로 검색한 지원서");
        return "application-list";
    }

    @ApiOperation(value = "작성중인 지원서 리스트 전체 조회 ")
    @GetMapping("/list/writing")
    public String applicationWritingList(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        // 내가 작성한 지원서 중 지원이 완료되지 않은 지원서를 검색한다. -> queryDSL을 이용해서 처리하였다.
        List<Application> applications = applicationQueryRepository.findWritingApplication(loginUser.getUserNo());
        model.addAttribute("sideUser", loginUser);
        model.addAttribute("applications", applications);
        model.addAttribute("pageTitle", "작성중인 지원서");
        return "application-list-writing";
    }

    @ApiOperation(value = "진행중인지원서 리스트 전체 조회 ")
    @GetMapping("/list/active")
    public String applicationActiveList(Model model, @SessionAttribute("user") SessionUser sessionUser){
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        // 내가 작성한 지원서 중, 현재 공고가 진행중인 지원서를 검색한다. -> 지원서 엔티티와 공고 엔티티를 조인하여 결과를 반환해야 하므로 queryDSL을 이용해서 처리하였다.
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
        // 세션에서 받아온 유저 정보로 해당 공고의 지원서를 작성하는 페이지를 띄운다.
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
        // 작성한 지원서를 임시저장 및 저장한다. db에 해당 유저가 공고에 대해 작성한 지원서가 있는지 검사하여 있으면 임시저장된 지원서로 판단하여 update, 없으면 save를 호출했다.
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        return applicationService.save(dto, loginUser, recruit);
    }

    @ApiOperation(value = "지원서 수정")
    @GetMapping("/edit/{applicationId}")
    public String edit(@PathVariable Long applicationId, Model model, @SessionAttribute("user") SessionUser sessionUser){
        // 세션에서 유저 정보를 받고, db에서 해당 지원서를 찾아 수정할 수 있는 페이지를 호출한다.
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
        // 프론트단의 ajax에서 전달해준 수정 내용을 dto에 담아 update한다. 
        return applicationService.update(applicationId, requestDto);
    }

    @ApiOperation(value = "지원서 삭제")
    @DeleteMapping("/delete/{applicationId}")
    @ResponseBody
    public Long delete(@PathVariable Long applicationId){
        // 해당 지원서가 존재하는지 검사하고, 있으면 service의 delete를 호출하여 삭제한다.
        Application application = applicationRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new NoResultException("no such application"));
        applicationService.delete(applicationId);
        return applicationId;
    }

}
