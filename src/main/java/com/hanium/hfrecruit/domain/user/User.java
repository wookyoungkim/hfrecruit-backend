package com.hanium.hfrecruit.domain.user;

import com.hanium.hfrecruit.domain.spec.PersonalSpec;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user")
public class User {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long userNo;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false)
    private String userPw;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String college;

    @Column(nullable = false)
    private String highschool;

    @Column(nullable = false)
    private String birth;

    @Column(nullable = false)
    private Integer gender;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<PersonalSpec> personalSpecs;

}
