package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.dto.CompanyInfoDto;
import com.hanium.hfrecruit.dto.RecruitResponseDto;
import com.hanium.hfrecruit.dto.RecruitSaveRequestDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
