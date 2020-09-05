package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.dto.RecruitSaveRequestDto;
import com.hanium.hfrecruit.dto.RecruitUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;

    @Transactional
    public Long save(RecruitSaveRequestDto requestDto){
        return recruitRepository.save(requestDto.toEntity()).getRecruitNo();
    }

//    @Transactional
//    public Long update(int id, RecruitUpdateRequestDto requestDto) {
//        Recruit recruit = RecruitRepository.findById(id).orElseThrow(()->IllegalArgumentException("공고가 없습니다. id="+id));
//        recruit.update(requestDto.get)
//    }
}
