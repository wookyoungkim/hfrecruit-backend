package com.hanium.hfrecruit;

import com.hanium.hfrecruit.domain.application.Application;
import com.hanium.hfrecruit.domain.application.ApplicationRepository;
import com.hanium.hfrecruit.domain.company.CompanyInfo;
import com.hanium.hfrecruit.domain.company.CompanyInfoRepository;
import com.hanium.hfrecruit.domain.company.CompanyUser;
import com.hanium.hfrecruit.domain.company.CompanyUserRepository;
import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.recruit.RecruitRepository;
import com.hanium.hfrecruit.domain.spec.PersonalSpec;
import com.hanium.hfrecruit.domain.spec.PersonalSpecRepository;
import com.hanium.hfrecruit.domain.spec.Spec;
import com.hanium.hfrecruit.domain.spec.SpecRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class SampleDataRunner implements CommandLineRunner {

    private final CompanyInfoRepository companyInfoRepository;
    private final CompanyUserRepository companyUserRepository;
    private final RecruitRepository recruitRepository;
    private final ApplicationRepository applicationRepository;
    private final SpecRepository specRepository;
    private final PersonalSpecRepository personalSpecRepository;

    @Autowired
    public SampleDataRunner(RecruitRepository recruitRepository, ApplicationRepository applicationRepository, SpecRepository specRepository, PersonalSpecRepository personalSpecRepository,
                            CompanyInfoRepository companyInfoRepository, CompanyUserRepository companyUserRepository) {
        this.companyInfoRepository = companyInfoRepository;
        this.companyUserRepository = companyUserRepository;
        this.recruitRepository = recruitRepository;
        this.applicationRepository = applicationRepository;
        this.specRepository = specRepository;
        this.personalSpecRepository = personalSpecRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        CompanyInfo companyInfoSample = CompanyInfo.builder()
                .companyEmail("company@naver.com")
                .companyLogo("nothing")
                .companyName("example_company")
                .managerId(1818)
                .build();
        companyInfoRepository.save(companyInfoSample);

        CompanyUser companyUserSample = CompanyUser.builder()
                .companyInfo(companyInfoSample)
                .companyUserCode(2)
                .build();
        companyUserRepository.save(companyUserSample);

        Recruit recruitSample = Recruit.builder()
                .recruitTitle("채용공고1")
                .startDate(java.sql.Timestamp.valueOf("2018-09-21 10:53:00.0"))
                .closedDate(java.sql.Timestamp.valueOf("2020-09-21 10:53:00.0"))
                .recruitContent("안녕하세요 abc회사입니다. 이번 지원에 어쩌구 저쩌구")
                .closedBit(0)
                .question1("회사 지원동기는?")
                .question2("대학교 생활 동안 가장 성취감 느낀 일은?")
                .question3("회사에 오면 하고 싶은 일은?")
                .companyInfo(companyInfoSample)
                .companyUser(companyUserSample)
                .build();
        recruitRepository.save(recruitSample);
//        Application applicationSample = Application.builder()
//                .q1Comment("저는 잘할자신이 있어요")
//                .q2Comment("스펙좋아요")
//                .q3Comment("꼭 뽑아주세요")
//                .bit(0)
//                .recruit(recruitSample)
//                .build();
//        applicationRepository.save(applicationSample);
        Spec specSample1 = Spec.builder()
                .specName("TOEIC")
                .institution("ACTFL")
                .build();
        specRepository.save(specSample1);
        Spec specSample2 = Spec.builder()
                .specName("OPIC")
                .institution("ACTFL")
                .build();
        specRepository.save(specSample2);
        PersonalSpec personalSpecSample = PersonalSpec.builder()
                .spec(specSample1)
                .score("900")
                .authDate("20200405")
                .certifiedDate("20210805")
                .build();
        personalSpecRepository.save(personalSpecSample);
    }


}
