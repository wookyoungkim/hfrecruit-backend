package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.ApplicationSaveRequestDto;
import com.hanium.hfrecruit.service.ApplicationService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
@RequestMapping("/application")
public class ApplicationPageController {

    @Autowired
    private final ApplicationService applicationService;
    private final RecruitRepository recruitRepository;
    private final UserRepository userRepository;
    private final HttpSession httpSession;

    @ApiOperation(value = "지원서 리스트 전체 조회 ")
    @GetMapping("/list")
    public String applicationList(){
        return "application";
    }

    @GetMapping("/apply/{recruitNo}")
    public String apply(@PathVariable Long recruitNo, Model model){
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));
        model.addAttribute("recruit", recruit);

        return "apply";
    }

    @ApiOperation(value = "지원서 작성")
    @PostMapping("/apply/{recruitNo}")
    @ResponseBody
    public Long save(@RequestBody ApplicationSaveRequestDto dto, @PathVariable Long recruitNo, HttpSession session){
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        Recruit recruit = recruitRepository.findByRecruitNo(recruitNo)
                .orElseThrow(() -> new NoResultException("error"));


        return applicationService.save(dto, loginUser, recruit);

    }
}
