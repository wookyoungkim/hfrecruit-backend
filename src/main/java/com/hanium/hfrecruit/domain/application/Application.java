package com.hanium.hfrecruit.domain.application;

import com.hanium.hfrecruit.domain.BaseTimeEntity;
import com.hanium.hfrecruit.domain.Recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
public class Application extends BaseTimeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long applicationId;

//    @Column(nullable = false)
//    private Date dateCreated;
//      BaseTimeEntity 에 속해있으므로 따로 정의할 필요가 없다.
//    @Column(nullable = false)
//    private Date lastUpdated;

    @Column(nullable = false)
    private Integer bit;

    @Column(nullable = false)
    private String question1;

    @Column(nullable = false)
    private String question2;

    @Column(nullable = false)
    private String question3;

    private String question4;

    private String question5;

    @Column(nullable = false)
    private String educationLevel;

    @Column(nullable = false)
    private String militaryService;

    private String q1Comment;

    private String q2Comment;

    private String q3Comment;

    private String q4Comment;

    private String q5Comment;

    private Integer score;

    private Integer passStage;

    private Integer passOrFail;

    @ManyToOne
    @JoinColumn(name = "recruit_no")
    private Recruit recruit;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;


}
