package com.hanium.hfrecruit.dto;

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
public class RecruitResponseDto {

    private Long recruitNo;
    private String recruitTitle;
    private String recruitContent;
    private LocalDateTime startDate;
    private LocalDateTime closedDate;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;

    public RecruitResponseDto(Recruit entity){
        this.recruitNo = entity.getRecruitNo();
        this.recruitTitle = entity.getRecruitTitle();
        this.recruitContent = entity.getRecruitContent();
        this.startDate = entity.getStartDate();
        this.closedDate = entity.getClosedDate();
        this.question1 = entity.getQuestion1();
        this.question2 = entity.getQuestion2();
        this.question3 = entity.getQuestion3();
        this.question4 = entity.getQuestion4();
        this.question5 = entity.getQuestion5();
    }

}
