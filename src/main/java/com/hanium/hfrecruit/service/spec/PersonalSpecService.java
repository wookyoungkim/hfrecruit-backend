package com.hanium.hfrecruit.service.spec;

import com.hanium.hfrecruit.domain.spec.PersonalSpecRepository;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import com.hanium.hfrecruit.dto.PersonalSpecDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class PersonalSpecService {

    private final PersonalSpecRepository personalSpecRepository;
    private final UserRepository userRepository;

    @Autowired
    public PersonalSpecService(PersonalSpecRepository personalSpecRepository, UserRepository userRepository) {
        this.personalSpecRepository = personalSpecRepository;
        this.userRepository = userRepository;
    }


    @Transactional(readOnly = true)
    public List<PersonalSpecDto> findAllSpecByUserNo(long userNo) {

        User user = userRepository.findUserByUserNo(userNo);
        return user.getPersonalSpecs().stream().map(PersonalSpecDto::new).collect(Collectors.toList());
    }

    public Long save(PersonalSpecDto personalSpecDto) {
        personalSpecRepository.save(personalSpecDto.toEntity());
        Long id = 12L;
        return id;
    }

    public PersonalSpecDto createWithParams(HashMap<String,Object> params) {
        PersonalSpecDto personalSpecDto = new PersonalSpecDto();
        personalSpecDto.setAuthDate((String)params.get("authDate"));
        personalSpecDto.setScore((String)params.get("score"));
        personalSpecDto.setCertifiedDate((String)params.get("certifiedDate"));
        return personalSpecDto;
    }
}
