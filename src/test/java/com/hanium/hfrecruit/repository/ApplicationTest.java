package com.hanium.hfrecruit.repository;

import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.application.ApplicationRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
public class ApplicationTest {

    @Autowired
    ApplicationRepository applicationRepository;

    @Test
    public void BaseTimeEntity_등록() {
        //given
        LocalDateTime now = LocalDateTime.of(2019, 12, 6, 0, 0, 0);
        Application application = Application.builder().bit(0)
                .question1("이 회사에 지원한 동기는?")
                .question2("대학교 생활 중 가장 뜻깊었던 성취는?")
                .question3("우리 회사에서 어떤 사람으로 성장하고 싶은지?")
                .educationLevel("석사졸")
                .militaryService("육군 만기 전역")
                .build();

        applicationRepository.save(application);

        //when
        List<Application> applications = applicationRepository.findAll();

        //then
        Application app = applications.get(0);

        System.out.println(">>>>> createDate = "+app.getCreatedDate() + ", modifiedDate = "+app.getModifiedDate());

//        assertThat(application.getCreatedDate()).isAf;
//        assertThat(application.getModifiedDate()).isAfter(now);
    }
}
