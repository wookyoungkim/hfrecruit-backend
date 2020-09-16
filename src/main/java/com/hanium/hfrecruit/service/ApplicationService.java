package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.application.ApplicationRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.dto.ApplicationListResponseDto;
import com.hanium.hfrecruit.dto.ApplicationSaveRequestDto;
import com.hanium.hfrecruit.dto.ApplicationUpdateRequestDto;
import com.hanium.hfrecruit.dto.UserUpdateRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class ApplicationService {
    private ApplicationRepository applicationRepository;

    //method내에서 exception발생시 해당 메소드에서 이루어진 모든 db작업 초기화 -> 모든 처리가 정상적으로 실행시에만 db에 커
    @Transactional
    //작성한 지원서의 id를 알 수 있도록 getId()를 반환으
    public Long save(ApplicationSaveRequestDto dto, User user, Recruit recruit) {
        Application application = applicationRepository.findByUserAndRecruit(user, recruit);

        if(application == null){
            return applicationRepository.save(dto.toEntity(user, recruit)).getApplicationId();
        }
        else{
            application.update(dto.getQ1Comment(), dto.getQ2Comment(), dto.getQ3Comment(), dto.getApplied());
            return application.getApplicationId();
        }
    }

    @Transactional(readOnly = true)
    public List<ApplicationListResponseDto> findAllDesc(User user){
        return applicationRepository.findAllByUser(user).stream()
                //repository 결과로 넘어온 application stream을 map을 통해 dto로 변환->list로 변환
                .map(ApplicationListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional
    public Long update(Long applicationId, ApplicationUpdateRequestDto requestDto) {
        Application application = applicationRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("no such application"));
        application.update(requestDto.getQ1Comment(), requestDto.getQ2Comment(), requestDto.getQ3Comment(), requestDto.getApplied());

        return applicationId;
    }

    @Transactional
    public void delete(Long applicationId) {
        Application application = applicationRepository.findByApplicationId(applicationId)
                .orElseThrow(() -> new IllegalArgumentException("no such application"));
        applicationRepository.delete(application);
    }
}
