package com.hanium.hfrecruit.dto;


import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class RecruitSaveRequestDto {
    private String recruitTitle;
    private String recruitContent;
    private LocalDateTime startDate;
    private LocalDateTime closedDate;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;
    private Integer closedBit;
    private CompanyInfo companyInfo;
    private CompanyUser companyUser;

    @Builder
    public RecruitSaveRequestDto(String recruitTitle, String recruitContent, LocalDateTime startDate, LocalDateTime closedDate,
                                 String question1, String question2, String question3, String question4, String question5, Integer closedBit,
                                 CompanyInfo companyInfo, CompanyUser companyUser){
        this.recruitTitle = recruitTitle;
        this.recruitContent = recruitContent;
        this.startDate = startDate;
        this.closedDate = closedDate;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
        this.closedBit = closedBit;
        this.companyInfo = companyInfo;
        this.companyUser = companyUser;
    }

    public Recruit toEntity(CompanyInfo companyInfo, CompanyUser companyUser) {
        return Recruit.builder()
                .recruitTitle(recruitTitle)
                .recruitContent(recruitContent)
                .startDate(startDate)
                .closedDate(closedDate)
                .question1(question1)
                .question2(question2)
                .question3(question3)
                .question4(question4)
                .question5(question5)
                .closedBit(null)
                .companyInfo(companyInfo)
                .companyUser(companyUser)
                .build();
    }

}
