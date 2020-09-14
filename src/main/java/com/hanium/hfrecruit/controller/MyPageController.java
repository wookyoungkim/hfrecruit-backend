package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
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

@Controller
@RequiredArgsConstructor
@RequestMapping
public class MyPageController {
    private final HttpSession httpSession;
    @Autowired
    private UserService userService;
    private final UserRepository userRepository;


    @GetMapping("/mypage")
    public String mypage(@SessionAttribute("user") SessionUser sessionUser, Model model){
        model.addAttribute("pageTitle", "마이페이지");
        return "mypage";
    }

    @GetMapping("/profile")
    public String profile(@SessionAttribute("user") SessionUser sessionUser, Model model){
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("finding userNo Failed!")
        );
        model.addAttribute("user", user);
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
    public String evaluate(){
        return "evaluationPage";
    }
}
