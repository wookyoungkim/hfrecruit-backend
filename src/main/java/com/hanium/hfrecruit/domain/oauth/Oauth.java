package com.hanium.hfrecruit.domain.oauth;

import com.hanium.hfrecruit.domain.user.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity

public class Oauth {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long tokenNo;

    @Column(nullable = false)
    private String accessToken;

    @ManyToOne
    @JoinColumn(name = "user_no")
    private User user;
}
