package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.dto.CompanyInfoUpdateDto;
import com.hanium.hfrecruit.dto.CompanyUserDto;
import com.hanium.hfrecruit.dto.CompanyUserUpdateDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyUserService {
    private final CompanyUserRepository companyUserRepository;

    @Transactional
    public Long save(CompanyUserDto companyUserDto, CompanyInfo companyInfo, User user) {
        return companyUserRepository.save(companyUserDto.toEntity(companyInfo, user)).getCompanyUserId();
    }

    @Transactional
    public Long update(Long companyUserNo, CompanyUserUpdateDto requestDto) {
        CompanyUser companyUser = companyUserRepository.findById(companyUserNo).orElseThrow(()->new IllegalArgumentException("유저가 없소"));
        companyUser.update(requestDto.getCompanyUserCode());
        return companyUserNo;
    }
}
