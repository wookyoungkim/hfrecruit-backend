package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequiredArgsConstructor
public class IndexPageController {
    private final HttpSession httpSession;
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;
    private final RecruitService recruitService;

//    RestController 는 page를 리턴안함.
    @GetMapping("/")
    public String index(Model model) {
        SessionUser sessionUser = (SessionUser) httpSession.getAttribute("user");
        model.addAttribute("pageTitle", "Home");

        LocalDateTime current = LocalDateTime.now();
        List<Recruit> closedRecruits = recruitRepository.findAll()
                .stream()
                .filter(recruit -> recruit.getClosedDate().isBefore(current))
                .collect(Collectors.toList());
        for(Recruit recruit: closedRecruits){
            recruitService.updateBit(recruit.getRecruitNo());
        }
        List<Recruit> activeRecruits = recruitRepository.findAll()
                .stream()
                .filter(recruit -> recruit.getClosedDate().isAfter(current))
                .collect(Collectors.toList());

        model.addAttribute("recruits", activeRecruits);
        if(sessionUser==null){
            model.addAttribute("sideUser", User.builder().name("비회원").build());
            return "index";
        }else {
            model.addAttribute("sideUser", userRepository.findByEmail(sessionUser.getEmail()));
            return "index-login";
        }
    }

    @GetMapping("/evaluation")
    public String ev(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", loginUser);

        model.addAttribute("pageTitle", "평가");
        return "evaluation";
    }
}
