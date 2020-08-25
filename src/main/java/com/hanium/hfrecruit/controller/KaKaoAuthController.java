package com.hanium.hfrecruit.controller;


import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by momentjin@gmail.com on 2020-03-29
 * Github : http://github.com/momentjin
 */
@RestController
@RequestMapping("/my")
public class KaKaoAuthController {

    @GetMapping
    public String getMyAuthenticationFromSession(@AuthenticationPrincipal OAuth2User oAuth2User) {
        return oAuth2User.toString();
    }
}
