package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyInfoService {
    private final CompanyInfoRepository companyInfoRepository;

    @Transactional
    public Long save(CompanyInfoDto requestDto){
        return companyInfoRepository.save(requestDto.toEntity()).getCompanyNo();
    }

    public CompanyInfoDto findByCompanyNo(Long companyNo) {
        return new CompanyInfoDto(companyInfoRepository.findByCompanyNo(companyNo));
    }

    @Transactional
    public Long update(Long companyNo, CompanyInfoUpdateDto companyInfoDto) {
        CompanyInfo companyInfo = companyInfoRepository.findById(companyNo).orElseThrow(()->new IllegalArgumentException("기업이 없습니다."));
        companyInfo.update(companyInfoDto.getCompanyName(), companyInfoDto.getCompanyEmail(), companyInfoDto.getCompanyPage(),
                companyInfoDto.getManagerId(), companyInfoDto.getCompanyLogo());
        return companyNo;
    }
}
