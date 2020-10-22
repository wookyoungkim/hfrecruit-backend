package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.transaction.Transactional;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class CompanyInfoDto {
    private Long companyNo;
    private String companyName;
    private String companyEmail;
    private String companyPage;
    private String managerId;
    private String companyLogo;
    private List<CompanyUser> companyUsersList;
    private List<Recruit> recruits;

    @Builder
    public CompanyInfoDto(CompanyInfo entity){
        this.companyNo = entity.getCompanyNo();
        this.companyName = entity.getCompanyName();
        this.companyEmail = entity.getCompanyEmail();
        this.companyPage = entity.getCompanyPage();
        this.managerId = entity.getManagerId();
        this.companyLogo = entity.getCompanyLogo();
        this.companyUsersList = entity.getCompanyUsersList();
        this.recruits = entity.getRecruits();
    }

    public CompanyInfo toEntity(){
        return CompanyInfo.builder()
                .companyNo(companyNo)
                .companyName(companyName)
                .companyEmail(companyEmail)
                .companyPage(companyPage)
                .managerId(managerId)
                .companyLogo(companyLogo)
                .companyUsersList(companyUsersList)
                .recruits(recruits)
                .build();
    }
}
