package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.recruit.Recruit;
import lombok.Getter;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
public class RecruitListResponseDto {
    private Long recruitNo;
    private String recruitTitle;
    private LocalDateTime closedDate;

    public RecruitListResponseDto(Recruit entity){
        this.recruitNo = entity.getRecruitNo();
        this.recruitTitle = entity.getRecruitTitle();
        this.closedDate = entity.getClosedDate();
    }
}
