package com.hanium.hfrecruit.domain.spec;

import com.hanium.hfrecruit.domain.user.User;
import lombok.*;

import javax.persistence.*;


@Getter
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class PersonalSpec {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long personalSpecId;

    @Column(nullable = false)
    private String certifiedDate;

    @Column(nullable = false)
    private String authDate;

    @Column(nullable = true)
    private String score;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;

    @ManyToOne
    @JoinColumn(name = "spec_id")
    private Spec spec;

}
