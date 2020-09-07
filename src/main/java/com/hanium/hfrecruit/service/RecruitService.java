package com.hanium.hfrecruit.service;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecruitService {

    private final RecruitRepository recruitRepository;
    private final CompanyInfoRepository companyInfoRepository;

    @Transactional
    public Long save(RecruitSaveRequestDto requestDto, CompanyInfo companyInfo, CompanyUser companyUser){
        return recruitRepository.save(requestDto.toEntity(companyInfo, companyUser)).getRecruitNo();
    }

    @Transactional(readOnly = true)
    public List<RecruitListResponseDto> findAll(){
        return recruitRepository.findAll().stream()
                .map(RecruitListResponseDto::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public RecruitResponseDto findOne(Long no){
        return new RecruitResponseDto(recruitRepository.getOne(no));
    }


    @Transactional
    public Long update(Long id, RecruitUpdateRequestDto requestDto) {
        Recruit recruit = recruitRepository.findById(id)
                .orElseThrow(()-> new IllegalArgumentException("공고가 없습니다. id="+id));
        return recruit.getRecruitNo();
//        recruit.update(requestDto.)
    }

}
