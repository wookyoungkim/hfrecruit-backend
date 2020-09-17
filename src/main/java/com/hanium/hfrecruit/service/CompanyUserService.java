package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.dto.CompanyUserDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyUserService {
    private final CompanyUserRepository companyUserRepository;

    public Long save(CompanyUserDto companyUserDto, CompanyInfo companyInfo, User user) {
        return companyUserRepository.save(companyUserDto.toEntity(companyInfo, user)).getCompanyUserId();
    }
}
