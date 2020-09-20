package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.application.ApplicationQueryRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.UserUpdateRequestDto;
import com.hanium.hfrecruit.service.UserService;
import io.swagger.models.auth.In;
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
    private final HttpSession httpSession;
    @Autowired
    private UserService userService;
    private final UserRepository userRepository;
    private final ApplicationQueryRepository applicationQueryRepository;


    @GetMapping("/mypage")
    public String mypage(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        Integer active = applicationQueryRepository.findActiveByRecruit(user.getUserNo()).size();
        Integer writing = applicationQueryRepository.findWritingApplication(user.getUserNo()).size();

        model.addAttribute("pageTitle", "마이페이지");
        model.addAttribute("user", user);
        model.addAttribute("sideUser", user);
        model.addAttribute("activeApplication", active);
        model.addAttribute("writingApplication", writing);
        return "userMypage";
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
        return "evaluationPage";
    }
}
