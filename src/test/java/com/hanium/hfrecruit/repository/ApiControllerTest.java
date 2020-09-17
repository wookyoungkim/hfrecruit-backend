package com.hanium.hfrecruit.repository;

import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.UserUpdateRequestDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApiControllerTest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    public void Posts_수정된다() throws Exception {
        User saveUser = new User();
        Role role = Role.USER;

        saveUser.setUsername("testuser03");
        saveUser.setEmail("test3");
        saveUser.setRole(role);
        User user = userRepository.save(saveUser);

        Long updateId = saveUser.getUserNo();
        String expectedUsername = "title2";
        String expectedBirth = "980903";
        String expectedAddress = "incheon";
        String expectedCollege = "hongik";
        String expectedEdu = "대졸";
        String expectedMil = "미필";
        String expectedGender = "여";

        UserUpdateRequestDto requestDto = UserUpdateRequestDto.builder()
                .username(expectedUsername)
                .birth(expectedBirth)
                .address(expectedAddress)
                .college(expectedCollege)
                .educationLevel(expectedEdu)
                .militaryService(expectedMil)
                .gender(expectedGender)
                .build();

        System.out.println("newUser: " + requestDto.getUsername());

        String url = "http://localhost:" + "8080" + "/userInfo/" + updateId;

        HttpEntity<UserUpdateRequestDto> requestEntity = new HttpEntity<>(requestDto);

        //when
        ResponseEntity<Long> responseEntity = testRestTemplate.exchange(url, HttpMethod.PUT, requestEntity, Long.class);

        //then
//        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(responseEntity.getBody()).isGreaterThan(0L);
//
//        assertThat(all.get(0).getUsername()).isEqualTo(expectedUsername);
//        assertThat(all.get(0).getBirth()).isEqualTo(expectedBirth);
    }
}