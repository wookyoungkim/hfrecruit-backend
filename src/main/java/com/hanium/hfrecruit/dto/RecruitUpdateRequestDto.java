package com.hanium.hfrecruit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
public class RecruitUpdateRequestDto {
    private String recruitTitle;
    private String recruitContent;
    private LocalDateTime startDate;
    private LocalDateTime closedDate;
    private String question1;
    private String question2;
    private String question3;
    private String question4;
    private String question5;

    @Builder
    public RecruitUpdateRequestDto(String recruitTitle, String recruitContent, LocalDateTime startDate, LocalDateTime closedDate,
                                   String question1, String question2, String question3, String question4, String question5) {
        this.recruitTitle = recruitTitle;
        this.recruitContent = recruitContent;
        this.startDate = startDate;
        this.closedDate = closedDate;
        this.question1 = question1;
        this.question2 = question2;
        this.question3 = question3;
        this.question4 = question4;
        this.question5 = question5;
    }
}
