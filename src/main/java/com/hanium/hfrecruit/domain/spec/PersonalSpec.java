package com.hanium.hfrecruit.domain.spec;

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
public class PersonalSpec {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long personalSpecId;

    @Column(nullable = false)
    private Date certifiedDate;

    @Column(nullable = false)
    private Date authDate;

    @Column(nullable = true)
    private Integer score;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Spec spec;

}
