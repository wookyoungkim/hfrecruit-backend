package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.user.User;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CompanyUserUpdateDto {
    private Integer companyUserCode;

    @Builder
    public CompanyUserUpdateDto(Integer companyUserCode){
        this.companyUserCode = companyUserCode;
    }
}
