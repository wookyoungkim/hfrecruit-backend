package com.hanium.hfrecruit.controller;


import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.dto.RecruitSaveRequestDto;
import com.hanium.hfrecruit.dto.RecruitUpdateRequestDto;
import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RecruitApiController {
    private final RecruitService recruitService;
    private final CompanyUserRepository companyUserRepository;
    private final CompanyInfoRepository companyInfoRepository;


    @PostMapping("/api/v1/recruit")
    public Long save(@RequestBody RecruitSaveRequestDto requestDto){
        CompanyUser companyUser = companyUserRepository.getOne((long) 1);   //이거 로그인 유저 되면 바꿔야함
        CompanyInfo companyInfo = companyInfoRepository.findByCompanyNo((long) 1);  //임시로 넣어둔 것!!
        return recruitService.save(requestDto, companyInfo, companyUser);
    }

    @PutMapping("/api/v1/recruit/{id}")
    public Long update(@PathVariable Long id, @RequestBody RecruitUpdateRequestDto requestDto){
        return recruitService.update(id, requestDto);
    }
}
