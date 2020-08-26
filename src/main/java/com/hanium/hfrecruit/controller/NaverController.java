package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.oauth2.naver.SessionUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpSession;

@RequiredArgsConstructor
@Controller
public class NaverController {

    @GetMapping("/")
    public String getAuthorizationMessage() { return "home"; }

}