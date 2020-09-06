package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.application.ApplicationRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.dto.ApplicationSaveRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@AllArgsConstructor
@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;

    //method내에서 exception발생시 해당 메소드에서 이루어진 모든 db작업 초기화 -> 모든 처리가 정상적으로 실행시에만 db에 커
    @Transactional
    //작성한 지원서의 id를 알 수 있도록 getId()를 반환으
    public Long save(ApplicationSaveRequestDto dto, User user, Recruit recruit) {
        return applicationRepository.save(dto.toEntity(user, recruit)).getApplicationId();
    }
}
