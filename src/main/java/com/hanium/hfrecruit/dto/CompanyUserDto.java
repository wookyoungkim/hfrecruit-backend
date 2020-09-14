package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CompanyUserDto {
    private Long companyUserId;
    private Integer companyUserCode;
    private CompanyInfo companyInfo;

    public CompanyUserDto(CompanyUser entity){
        this.companyUserId = entity.getCompanyUserId();
        this.companyInfo = entity.getCompanyInfo();
    }

    public CompanyUser toEntity(CompanyInfo companyInfo){
        return CompanyUser.builder()
                .companyUserId(companyUserId)
                .companyInfo(companyInfo)
                .build();
    }
}
