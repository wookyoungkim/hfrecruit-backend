package com.hanium.hfrecruit.dto;


import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
public class ApplicationSaveRequestDto {

    private Integer bit;
    private String q1Comment;
    private String q2Comment;
    private String q3Comment;
    private Integer score;
    private Integer passStage;
    private Integer applied;
    private String q1Feedback;
    private String q2Feedback;
    private String q3Feedback;
    private Recruit recruit;
    private User user;

    public ApplicationSaveRequestDto(Integer bit, String q1Comment, String q2Comment, String q3Comment,
                                     Integer score, Integer passStage, Integer applied,
                                     String q1Feedback, String q2Feedback, String q3Feedback,
                                     Recruit recruit, User user){
        this.bit = bit;
        this.q1Comment = q1Comment;
        this.q2Comment = q2Comment;
        this.q3Comment = q3Comment;
        this.score = score;
        this.applied = applied;
        this.q1Feedback = q1Feedback;
        this.q2Feedback = q2Feedback;
        this.q3Feedback = q3Feedback;
        this.recruit = recruit;
        this.user = user;
    }

    public Application toEntity(User user, Recruit recruit){
        return Application.builder()
                .bit(bit)
                .q1Comment(q1Comment)
                .q2Comment(q2Comment)
                .q3Comment(q3Comment)
                .score(score)
                .applied(applied)
                .q1Feedback(q1Feedback)
                .q2Feedback(q2Feedback)
                .q3Feedback(q3Feedback)
                .recruit(recruit)
                .user(user)
                .build();
    }
}
