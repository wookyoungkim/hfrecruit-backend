package com.hanium.hfrecruit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
@Getter
@NoArgsConstructor
public class EvaluationSaveRequestDto {

    private Integer bit;

    private String q1Feedback;

    private String q2Feedback;

    private String q3Feedback;

    private Integer score;

    private Integer passStage;

    private Integer passOrFail;


    @Builder
    public EvaluationSaveRequestDto(Integer bit, String q1Feedback, String q2Feedback, String q3Feedback, Integer score, Integer passStage, Integer passOrFail){
        this.bit = bit;
        this.q1Feedback = q1Feedback;
        this.q2Feedback = q2Feedback;
        this.q3Feedback = q3Feedback;
        this.score = score;
        this.passStage = passStage;
        this.passOrFail = passOrFail;

    }
}
