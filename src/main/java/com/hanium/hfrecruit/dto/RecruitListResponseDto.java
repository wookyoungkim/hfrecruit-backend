package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.recruit.Recruit;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class RecruitListResponseDto {
    private Long recruitNo;
    private String recruitTitle;
    private Timestamp closedDate;

    public RecruitListResponseDto(Recruit entity){
        this.recruitNo = entity.getRecruitNo();
        this.recruitTitle = entity.getRecruitTitle();
        this.closedDate = entity.getClosedDate();
    }
}
