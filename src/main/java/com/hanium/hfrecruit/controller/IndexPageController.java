package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor
public class IndexPageController {
    private final HttpSession httpSession;
    private final UserRepository userRepository;
//    RestController 는 page를 리턴안함.
    @GetMapping("/")
    public String index(Model model) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        if(sessionUser==null){
            model.addAttribute("sideUser", User.builder().name("비회원").build());
        }else {
            model.addAttribute("sideUser", userRepository.findByEmail(sessionUser.getEmail()));
        }
        model.addAttribute("pageTitle", "Home");
        return "index";
    }

    @GetMapping("/evaluation")
    public String ev(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", loginUser);

        model.addAttribute("pageTitle", "평가");
        return "evaluation";
    }
}
