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

    @Column(nullable = false)
    private String q1Comment;

    @Column(nullable = false)
    private String q2Comment;

    @Column(nullable = false)
    private String q3Comment;

    private Integer score;

    private Integer passStage;

    private Integer passOrFail;

    @ManyToOne
    @JoinColumn(name = "recruit_no")
    private Recruit recruit;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @Builder
    public Application(Long applicationId, Integer bit, String q1Comment, String q2Comment, String q3Comment, Recruit recruit, User user) {
        this.applicationId = applicationId;
        this.bit = bit;
        this.q1Comment = q1Comment;
        this.q2Comment = q2Comment;
        this.q3Comment = q3Comment;
        this.recruit = recruit;
        this.user = user;
    }


}
