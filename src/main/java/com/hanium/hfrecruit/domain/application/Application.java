package com.hanium.hfrecruit.domain.application;

import com.hanium.hfrecruit.domain.BaseTimeEntity;
import com.hanium.hfrecruit.domain.recruit.Recruit;
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

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String q1Comment;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String q2Comment;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String q3Comment;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String q1Feedback;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String q2Feedback;

    @Column(columnDefinition = "LONGTEXT", nullable = false)
    private String q3Feedback;

    private Integer score;

    private Integer passStage;

    private Integer passOrFail;

    private Integer applied;

    @ManyToOne
    @JoinColumn(name = "recruit_no")
    private Recruit recruit;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Builder
    public Application(Integer bit, String q1Comment, String q2Comment, String q3Comment,
                       Integer score, Integer applied,
                       String q1Feedback, String q2Feedback, String q3Feedback,
                       Recruit recruit, User user) {
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

    public void update(String q1Comment, String q2Comment, String q3Comment, Integer applied){
        this.q1Comment = q1Comment;
        this.q2Comment = q2Comment;
        this.q3Comment = q3Comment;
        this.applied = applied;
    }

    public void evaluate(Integer bit, String q1Feedback, String q2Feedback, String q3Feedback, Integer score, Integer passStage, Integer passOrFail){
        this.bit = bit;
        this.q1Feedback = q1Feedback;
        this.q2Feedback = q2Feedback;
        this.q3Feedback = q3Feedback;
        this.score = score;
        this.passStage = passStage;
        this.passOrFail = passOrFail;
    }

}
