package com.hanium.hfrecruit.dto;

import com.hanium.hfrecruit.domain.user.User;
import lombok.Getter;


@Getter
public class UserResponseDto {
    private Long userNo;
    private String userId;
    private String userPw;
    private String username;
    private String email;
    private String address;
    private String college;
    private String militaryService;
    private String educationLevel;
    private String highschool;
    private String birth;
    private Integer gender;

    public UserResponseDto(User entity) {
        this.userNo = entity.getUserNo();
        this.userId = entity.getUserId();
        this.userPw = entity.getUserPw();
        this.username = entity.getUsername();
        this.email = entity.getEmail();
        this.address = entity.getAddress();
        this.college = entity.getCollege();
        this.militaryService = entity.getMilitaryService();
        this.educationLevel = entity.getEducationLevel();
        this.highschool = entity.getHighschool();
        this.birth = entity.getBirth();
        this.gender = entity.getGender();
    }
}
