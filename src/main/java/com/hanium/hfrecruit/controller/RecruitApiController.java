package com.hanium.hfrecruit.controller;


import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.dto.CompanyInfoDto;
import com.hanium.hfrecruit.dto.RecruitResponseDto;
import com.hanium.hfrecruit.dto.RecruitSaveRequestDto;
import com.hanium.hfrecruit.dto.RecruitUpdateRequestDto;
import com.hanium.hfrecruit.service.RecruitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RecruitApiController {
    private final RecruitService recruitService;

    @PostMapping("/api/v1/recruit")
    public Long save(@RequestBody RecruitSaveRequestDto requestDto){
        return recruitService.save(requestDto);
    }

//    @RequestMapping(value="/companyInfo/{no}", method = RequestMethod.GET)
//    public @ResponseBody
//    CompanyInfo getCompanyNo(@PathVariable Long no){
//        return recruitService.getCompanyNo(no);
//    }

    @RequestMapping("/companyInfo/{id}")
    public Long getNo(@PathVariable Long id, @RequestBody CompanyInfoDto companyInfoDto){
        return recruitService.getCompanyNo(id, companyInfoDto).getCompanyNo();
    }

    @PutMapping("/api/v1/recruit/{id}")
    public Long update(@PathVariable Long id, @RequestBody RecruitUpdateRequestDto requestDto){
        return recruitService.update(id, requestDto);
    }
}
