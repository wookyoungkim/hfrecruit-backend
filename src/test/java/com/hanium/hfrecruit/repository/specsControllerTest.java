package com.hanium.hfrecruit.repository;

import com.hanium.hfrecruit.domain.spec.PersonalSpec;
import com.hanium.hfrecruit.domain.spec.PersonalSpecRepository;
import com.hanium.hfrecruit.domain.spec.SpecRepository;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.PersonalSpecDto;
import com.hanium.hfrecruit.dto.SpecDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class specsControllerTest {

    @Autowired
    public UserRepository userRepository;
    @Autowired
    public SpecRepository specRepository;
    @Autowired
    public PersonalSpecRepository personalSpecRepository;

    @Test
    public void 유저_스펙_개인스펙_등록및연결_테스트(){
        Role role = Role.USER;
        User expectedUser = User.builder()
                .role(role)
                .name("choi")
                .email("123@naver.com")
                .build();
        User user = userRepository.save(expectedUser);
        log.info(user.getUsername()+" is created.");

        SpecDto specDto = new SpecDto(1L,"한국사","협회",null);

        specRepository.save(specDto.toEntity());
        specRepository.findAll().forEach((s)->{ log.info(s.getSpecId() + " : " + s.getSpecName()); });
        PersonalSpecDto personalSpecDto = new PersonalSpecDto(1L,"20201102","20200302","890");

        personalSpecDto.setSpec(specRepository.findBySpecId(1L));
        PersonalSpec personalSpec = personalSpecRepository.save(personalSpecDto.toEntity());
        personalSpecRepository.findAll().forEach((s)->{ log.info(s.getSpec().getSpecName() + " : " + s.getScore()); });
    }

}
