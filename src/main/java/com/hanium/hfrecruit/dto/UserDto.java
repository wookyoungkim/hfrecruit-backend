package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.spec.PersonalSpec;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import lombok.Getter;

import java.util.List;

@Getter
public class UserDto {
    private Long userNo;

    private String userId;

    private String userPw;

    private String userName;

    private String email;

    private String college;

    private String highschool;

    private String birth;

    private Integer gender;

    private Role role;

    private List<PersonalSpec> personalSpecs;

    public UserDto(User entity) {
        this.userNo = entity.getUserNo();
        this.userId = entity.getUserId();
        this.userPw = entity.getUserPw();
        this.userName = entity.getUsername();
        this.email = entity.getEmail();
        this.college = entity.getCollege();
        this.highschool = entity.getHighschool();
        this.birth = entity.getBirth();
        this.gender = entity.getGender();
        this.role = entity.getRole();
        this.personalSpecs = entity.getPersonalSpecs();
    }
}
