package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Getter
public class ApplicationListResponseDto {
    private Long applicationId;
    private Integer bit;
    private String q1Comment;
    private String q2Comment;
    private String q3Comment;
    private Integer score;
    private Integer passStage;
    private Integer passOrFail;
    private Recruit recruit;
    private User user;

    public ApplicationListResponseDto(Application entity) {
        this.applicationId = entity.getApplicationId();
        this.bit = entity.getBit();
        this.q1Comment = entity.getQ1Comment();
        this.q2Comment = entity.getQ2Comment();
        this.q3Comment = entity.getQ3Comment();
        this.score = entity.getScore();
        this.passStage = entity.getPassStage();
        this.passOrFail = entity.getPassOrFail();
        this.recruit = entity.getRecruit();
        this.user = entity.getUser();
    }
}
