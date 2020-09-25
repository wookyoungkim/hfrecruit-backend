package com.hanium.hfrecruit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CompanyInfoUpdateDto {
    private String companyName;
    private String companyEmail;
    private String companyPage;
    private String managerId;
    private Long companyLogo;

    @Builder
    public CompanyInfoUpdateDto(String companyName, String companyEmail, String companyPage, String managerId, Long companyLogo) {
        this.companyName = companyName;
        this.companyEmail = companyEmail;
        this.companyPage = companyPage;
        this.managerId = managerId;
        this.companyLogo = companyLogo;
    }
}