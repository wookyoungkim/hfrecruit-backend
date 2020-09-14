package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.CompanyUserDto;
import com.hanium.hfrecruit.dto.PersonalSpecDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Slf4j
@Service
@RequiredArgsConstructor
public class CompanyUserService {
    private final CompanyUserRepository companyUserRepository;

    public Long save(CompanyUserDto companyUserDto, CompanyInfo companyInfo) {
        return companyUserRepository.save(companyUserDto.toEntity(companyInfo)).getCompanyUserId();
    }

    public CompanyUserDto createWithParams(HashMap<String,Object> params) {
        CompanyUserDto companyUserDto = new CompanyUserDto();
        companyUserDto.setCompanyUserCode(1);
        companyUserDto.setCompanyUserId((Long) params.get("companyUserId"));
        return companyUserDto;
    }
}
