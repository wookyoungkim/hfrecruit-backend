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

    @Transactional
    public Long update(Long recruitNo, RecruitUpdateRequestDto requestDto) {
        Recruit recruit = recruitRepository.findById(recruitNo)
                .orElseThrow(()-> new IllegalArgumentException("공고가 없습니다. id="+recruitNo));
        recruit.update(requestDto.getRecruitTitle(), requestDto.getRecruitContent(), requestDto.getStartDate(), requestDto.getClosedDate(),
                requestDto.getQuestion1(), requestDto.getQuestion2(), requestDto.getQuestion3(), requestDto.getQuestion4(), requestDto.getQuestion5());
        return recruitNo;
    }

    @Transactional
    public void delete(Long recruitNo) {
        Recruit recruit = recruitRepository.findById(recruitNo)
                .orElseThrow(()-> new IllegalArgumentException("공고가 없습니다. id="+recruitNo));
        recruitRepository.delete(recruit);
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

    public RecruitResponseDto findById(Long recruitNo){
        Recruit entity = recruitRepository.findById(recruitNo)
                .orElseThrow(()-> new IllegalArgumentException("사용자가 없습니다. id="+recruitNo));
        return new RecruitResponseDto(entity);
    }
}
