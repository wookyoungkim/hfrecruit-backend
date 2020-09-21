package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.CompanyUserDto;
import com.hanium.hfrecruit.dto.PersonalSpecDto;
import com.hanium.hfrecruit.dto.UserResponseDto;
import com.hanium.hfrecruit.dto.UserUpdateRequestDto;
import com.hanium.hfrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpSession;
import java.util.HashMap;

@Controller
@RequiredArgsConstructor
public class OAuthController {
    @Autowired
    private UserService userService;
    private final UserRepository userRepository;

    @GetMapping({"", "/home"})
    public String home(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        if (sessionUser != null) {
            model.addAttribute("sideUser", userRepository.findByEmail(sessionUser.getEmail()));
            model.addAttribute("pageTitle", "Home");
        }
        return "home";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/userInfo")
    public String userInfo(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        if (sessionUser != null) {
            User user = userRepository.findByEmail(sessionUser.getEmail())
                    .orElseThrow(() -> new NoResultException("erroror"));
            if (user.getAddress() == null && user.getBirth() == null && user.getCollege() == null && user.getEducationLevel() == null && user.getGender() == null && user.getHighschool() == null && user.getMilitaryService() == null)
                return "/";
            model.addAttribute("userNo", user.getUserNo());
            model.addAttribute("userName", user.getUsername());
            model.addAttribute("pageTitle", "추가 정보 입력");
            model.addAttribute("sideUser", user);
        }
        return "userInfo";
    }

    @PutMapping("/userInfo/save")
    @ResponseBody
    public Long update(@SessionAttribute("user") SessionUser sessionUser, @RequestBody UserUpdateRequestDto requestDto) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        return userService.update(loginUser.getUserNo(), requestDto);
    }

    @PutMapping("/user/delete")
    @ResponseBody
    public Long withdrawal(@SessionAttribute("user") SessionUser sessionUser) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail())
                .orElseThrow(() -> new NoResultException("error"));
        return userService.withdrawal(loginUser.getUserNo());
    }

    @GetMapping("/loginFailure")
    public String loginFailure() {
        return "loginFailure";
    }
}