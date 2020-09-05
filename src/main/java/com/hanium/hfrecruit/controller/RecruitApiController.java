package com.hanium.hfrecruit.controller;


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

//    @PutMapping("/api/v1/recruit/{id}")
//    public Long update(@PathVariable Long id, @RequestBody RecruitUpdateRequestDto requestDto){
//        return recruitService.update(id, requestDto);
//    }
//
//    @GetMapping("/api/v1/recruit/{id}")
//    public RecruitResponseDto findById (@PathVariable Long id){
//        return recruitService.findById(id);
//    }

}
