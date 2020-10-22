package com.hanium.hfrecruit.controller;

import com.hanium.hfrecruit.auth.dto.SessionUser;
import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.CompanyInfoDto;
import com.hanium.hfrecruit.dto.CompanyInfoUpdateDto;
import com.hanium.hfrecruit.dto.CompanyUserDto;
import com.hanium.hfrecruit.dto.CompanyUserUpdateDto;
import com.hanium.hfrecruit.service.CompanyInfoService;
import com.hanium.hfrecruit.service.CompanyUserService;
import com.hanium.hfrecruit.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequiredArgsConstructor
public class CompanyUserController {
    private final CompanyUserService companyUserService;
    private final CompanyInfoService companyInfoService;
    private final CompanyInfoRepository companyInfoRepository;
    private final CompanyUserRepository companyUserRepository;
    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping("/companyInfo")
    public String companyInfo(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(() -> new IllegalArgumentException("NO USER!"));
        model.addAttribute("companyUser", loginUser.getEmail());
        model.addAttribute("pageTitle", "새로운 기업 등록");
        return "companyInfo";
    }

    @GetMapping("/companyUser")
    public String companyUser(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User sideUser = userRepository.findByEmail(sessionUser.getEmail()).orElse(User.builder().name("비회원").build());
        model.addAttribute("sideUser", sideUser);

        model.addAttribute("companyInfo", companyInfoRepository.findAll());
        model.addAttribute("companyUser", companyUserRepository.findAll());
        model.addAttribute("pageTitle", "기업회원가입");
        return "companyUser";
    }

    @ResponseBody
    @PostMapping("/companyUser/save/{companyNo}")
    public Long CompanyUserSave(@SessionAttribute("user") SessionUser sessionUser, @RequestBody CompanyUserDto companyUserDto, @PathVariable Long companyNo) {
        User loginUser = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(()-> new IllegalArgumentException("NO USER!"));
        CompanyInfo companyInfo = companyInfoRepository.findByCompanyNo(companyNo);
        userService.updateRole(loginUser.getUserNo());
        return companyUserService.save(companyUserDto, companyInfo, loginUser);
    }

    @ResponseBody
    @PostMapping("/companyInfo/save")
    public Long companyInfoSave(Model model, @RequestBody CompanyInfoDto companyInfoDto
                                //, @RequestParam("file")MultipartFile files)
    ){
//        try {
//            String origFilename = files.getOriginalFilename();
//            String filename = new MD5Generator(origFilename).toString();
//            String savePath = System.getProperty("user.dir") + "\\files";
////            if (!new File(savePath).exists()) {
////                try{
////                    new File(savePath).mkdir();
////                }
////                catch(Exception e){
////                    e.getStackTrace();
////                }
////            }
//            String filePath = savePath + "\\" + filename;
//            //files.transferTo(new File(filePath));
//
//            FileDto fileDto = new FileDto();
//            fileDto.setOrigFilename(origFilename);
//            fileDto.setFilename(filename);
//            fileDto.setFilePath(filePath);
//
//            Long fileId = fileService.saveFile(fileDto);
//            companyInfoDto.setCompanyLogo(fileId);
//        } catch(Exception e) {
//            e.printStackTrace();
//        }
        return companyInfoService.save(companyInfoDto);
    }

    @GetMapping("/company-info-update")
    public String companyInfoUpdate(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("유저가 아니면 이 페이지에 못와요.")
        );
        CompanyInfo companyInfo = companyInfoRepository.findByCompanyNo(companyUserRepository.findByCompanyUserEmail(user.getEmail()).getCompanyInfo().getCompanyNo());
        model.addAttribute("user", user);
        model.addAttribute("sideUser", user);
        model.addAttribute("company", companyInfo);
        model.addAttribute("pageTitle", "기업정보수정");
        return "companyInfo-update";
    }

    @GetMapping("/company-user-add")
    public String companyUserAdd(Model model, @SessionAttribute("user") SessionUser sessionUser) {
        User user = userRepository.findByEmail(sessionUser.getEmail()).orElseThrow(
                () -> new IllegalArgumentException("유저가 아니면 이 페이지에 못와요.")
        );
        CompanyInfo companyInfo = companyInfoRepository.findByCompanyNo((long) companyInfoRepository.findAll().size());
        model.addAttribute("sideUser", user);
        model.addAttribute("companyInfo", companyInfo);
        model.addAttribute("pageTitle", "새로운 기업 사용자 등록");
        return "company-user-add";
    }

    @PutMapping("/mypage/company-info-update/{companyNo}")
    @ResponseBody
    public Long companyInfoUpdate(@PathVariable Long companyNo, @RequestBody CompanyInfoUpdateDto requestDto) {
        return companyInfoService.update(companyNo, requestDto);
    }

    @PutMapping("/companyUser/update/{companyUserNo}")
    @ResponseBody
    public Long companyUserUpdate(@PathVariable Long companyUserNo, @RequestBody CompanyUserUpdateDto requestDto) {
        return companyUserService.update(companyUserNo, requestDto);
    }
}
