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
    private String q4Comment;
    private Recruit recruit;
    private User user;

    public ApplicationSaveRequestDto(Integer bit, String q1Comment, String q2Comment, String q3Comment, Recruit recruit, User user){
        this.bit = bit;
        this.q1Comment = q1Comment;
        this.q2Comment = q2Comment;
        this.q3Comment = q3Comment;
        this.recruit = recruit;
        this.user = user;
    }

    public Application toEntity(User user, Recruit recruit){
        return Application.builder()
                .bit(bit)
                .q1Comment(q1Comment)
                .q2Comment(q2Comment)
                .q3Comment(q3Comment)
                .recruit(recruit)
                .user(user)
                .build();
    }
}
