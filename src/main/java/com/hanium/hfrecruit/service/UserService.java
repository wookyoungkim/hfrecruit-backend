package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.auth.dto.OAuthAttributes;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.UserResponseDto;
import com.hanium.hfrecruit.dto.UserUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;
import javax.transaction.Transactional;
import java.util.NoSuchElementException;


@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public Long update(Long userNo, UserUpdateRequestDto requestDto) {
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("erroror"));
        user.update(requestDto.getUsername(), requestDto.getBirth(), requestDto.getAddress(), requestDto.getCollege(), requestDto.getHighschool(), requestDto.getEducationLevel(), requestDto.getMilitaryService(), requestDto.getGender());

        return userNo;
    }

    @Transactional
    public void updateRole(Long userNo){
        User user = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("user 업데이트 실패"));
        user.updateRole(Role.COMPANYUSER);
    }

    public UserResponseDto findByUserNo(Long userNo) {
        User entity = userRepository.findByUserNo(userNo)
                .orElseThrow(() -> new IllegalArgumentException("erroror"));
        return new UserResponseDto(entity);
    }
}
