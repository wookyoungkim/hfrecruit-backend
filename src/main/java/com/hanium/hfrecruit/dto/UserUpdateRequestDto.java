package com.hanium.hfrecruit.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class UserUpdateRequestDto {

    private String username;
    private String birth;
    private String address;
    private String college;
    private String highschool;
    private String educationLevel;
    private String militaryService;
    private Integer gender;

    @Builder
    public UserUpdateRequestDto(String username, String birth,String address,String college, String highschool, String educationLevel, String militaryService, Integer gender){
        this.username = username;
        this.birth = birth;
        this.address = address;
        this.college = college;
        this.highschool = highschool;
        this.educationLevel = educationLevel;
        this.militaryService = militaryService;
        this.gender = gender;
    }
}
