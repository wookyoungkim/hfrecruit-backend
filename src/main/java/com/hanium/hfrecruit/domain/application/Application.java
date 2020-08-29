package com.hanium.hfrecruit.domain.application;

import com.hanium.hfrecruit.domain.Recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Application {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long applicationId;

    @Column(nullable = false)
    private Date dateCreated;

    @Column(nullable = false)
    private Date lastUpdated;

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
