package com.hanium.hfrecruit.auth.dto;

import com.hanium.hfrecruit.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private String picture;

    public SessionUser(User user) {
        this.name = user.getUsername();
        this.email = user.getEmail();
    }
}