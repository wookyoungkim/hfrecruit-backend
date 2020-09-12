package com.hanium.hfrecruit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ApplicationUpdateRequestDto {

    private String q1Comment;
    private String q2Comment;
    private String q3Comment;


    @Builder
    public ApplicationUpdateRequestDto(String q1Comment, String q2Comment,String q3Comment){
        this.q1Comment = q1Comment;
        this.q2Comment = q2Comment;
        this.q3Comment = q3Comment;
    }
}
