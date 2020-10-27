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
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Slf4j
@Component
public class SampleDataRunner implements CommandLineRunner {

    private final CompanyInfoRepository companyInfoRepository;
    private final CompanyUserRepository companyUserRepository;
    private final UserRepository userRepository;
    private final RecruitRepository recruitRepository;
    private final ApplicationRepository applicationRepository;
    private final SpecRepository specRepository;
    private final PersonalSpecRepository personalSpecRepository;

    @Autowired
    public SampleDataRunner(RecruitRepository recruitRepository, ApplicationRepository applicationRepository, SpecRepository specRepository, PersonalSpecRepository personalSpecRepository,
                            CompanyInfoRepository companyInfoRepository, CompanyUserRepository companyUserRepository, UserRepository userRepository) {
        this.companyInfoRepository = companyInfoRepository;
        this.companyUserRepository = companyUserRepository;
        this.userRepository = userRepository;
        this.recruitRepository = recruitRepository;
        this.applicationRepository = applicationRepository;
        this.specRepository = specRepository;
        this.personalSpecRepository = personalSpecRepository;
    }


    @Override
    public void run(String... args) throws Exception {
        User userSample = User.builder()
                .email("dusdn1702@naver.com")
                .name("김이음")
                .userState(1)
                .role(Role.COMPANYUSER)
                .build();
        userRepository.save(userSample);

        CompanyInfo companyInfoSample = CompanyInfo.builder()
                .companyEmail("ICT@gmail.com")
                .companyPage("https://www.hanium.or.kr/")
                .companyLogo("/images/ICT_logo.png")
                .companyName("ICT멘토링")
                .managerId("dusdn1702@naver.com")
                .build();
        companyInfoRepository.save(companyInfoSample);

        CompanyInfo companyInfoSample2 = CompanyInfo.builder()
                .companyEmail("hanium@gmail.com")
                .companyPage("https://www.hanium.or.kr/")
                .companyLogo("/images/H-logo.png")
                .companyName("한이음")
                .managerId("dusdn1702")
                .build();
        companyInfoRepository.save(companyInfoSample2);

        CompanyUser companyUserSample = CompanyUser.builder()
                .companyInfo(companyInfoSample)
                .companyUserCode(2)
                .companyUserEmail(userSample.getEmail())
                .build();
        companyUserRepository.save(companyUserSample);

        CompanyUser companyUserSample2 = CompanyUser.builder()
                .companyInfo(companyInfoSample2)
                .companyUserCode(2)
                .companyUserEmail(userSample.getEmail())
                .build();
        companyUserRepository.save(companyUserSample2);

        Recruit recruitSample2 = Recruit.builder()
                .recruitTitle("한이음 프로젝트 관리부 정직원 채용")
                .startDate(LocalDateTime.parse("2020-10-15T17:00:00"))
                .closedDate(LocalDateTime.parse("2020-10-31T17:00:00"))
                .recruitContent("  안녕하세요. 대학생 멘티와 지도교수, 기업전문가 ICT멘토가 팀을 이루어 현업 실무 기술이반영된 프로젝트를 " +
                        "수행하는 ICT인재양성 프로그램으로 학생은 다양한 ICT분야의 기업전문가 멘토에게 지도 받을 수 있는 기회를, " +
                        "멘토는 ICT분야에 관심 있는 대학생들에게 실무노하우를 전수하고 비전을 제시해 주도록 지원하고 있는 ICT 멘토링 사무국입니다." +
                        "현재 ICT멘토링 사업과 ICT인턴십 사업을 진행하고 있습니다." +
                        "2004년부터 현재까지 약 1,700여개 국내 IT기업에 소속된 3,000여명의 전문가가 ICT멘토로 참여하여 매년 1,000여건의 프로젝트를 대학과 같이 진행하고 있으며, " +
                        "현재까지 약 2만여명의 멘티(학생)을 지도하고 있는 미래창조과학부 소속 사업입니다." +
                        "\n 한이음은 스마트 해상물류, 프로보노, 이브와와 함께 운영되는 ICT 멘토링 중 가장 대규모의 멘토링 사업입니다." +
                        "\n\t1. 채용인원" +
                        "\n\t\t00명" +
                        "\n\t2. 채용유형" +
                        "\n\t\t한이음 프로젝트 관리부 정직원" +
                        "\n\t3. 접수기한" +
                        "\n\t\t2020년 10월 15일 오후 5시 ~ 2020년 10월 22일 오후 5시" +
                        "\n\t4. 접수방법" +
                        "\n\t\t본 서비스를 통한 온라인 지원" +
                        "\n\t5. 담당업무" +
                        "\n\t\t한이음 사무국 프로젝트 관리부 소속으로 멘토링 프로젝트 지원금, 진행 상황, 공모전 등 관리" +
                        "\n\t6.지원자격" +
                        "\n\t\t기졸업자 또는 졸업 예정자" +
                        "\n\t7. 자격요건" +
                        "\n\t\t없음" +
                        "\n\t8. 우대사항" +
                        "\n\t\t관련 업무 경험자" +
                        "\n\t관련문의" +
                        "\n\t\t연락처 : 02-2046-1452~9" + "\n\t\t문의메일 : hanium@fkii.org"
                )
                .closedBit(null)
                .question1("회사를 지원하게 된 동기는 무엇인가요? 간단한 자기소개와 함께 작성해 주세요. (1000자 이내)")
                .question2("가장 성취감을 느낀 일은 무엇이 있나요? 구체적인 사례를 들어 설명해주세요. (1500자 이내)")
                .question3("가장 실패감을 느낀 일은 무엇이 있나요? 구체적인 사례를 들어 설명해주세요. (1500자 이내)")
                .question4("입사하게 된다면 가장 하고 싶은 일은 무엇인가요? 구체적인 설명과 함께 작성해주세요. (1500자 이내)")
                .companyInfo(companyInfoSample2)
                .companyUser(companyUserSample2)
                .build();
        recruitRepository.save(recruitSample2);

        Recruit recruitSample = Recruit.builder()
                .recruitTitle("ICT 멘토링 홈페이지 관리부 인턴 채용")
                .startDate(LocalDateTime.parse("2020-10-22T17:00:00"))
                .closedDate(LocalDateTime.parse("2020-10-30T17:00:00"))
                .recruitContent("  안녕하세요. 대학생 멘티와 지도교수, 기업전문가 ICT멘토가 팀을 이루어 현업 실무 기술이반영된 프로젝트를 " +
                        "수행하는 ICT인재양성 프로그램으로 학생은 다양한 ICT분야의 기업전문가 멘토에게 지도 받을 수 있는 기회를, " +
                        "멘토는 ICT분야에 관심 있는 대학생들에게 실무노하우를 전수하고 비전을 제시해 주도록 지원하고 있는 ICT 멘토링 사무국입니다." +
                        "현재 ICT멘토링 사업과 ICT인턴십 사업을 진행하고 있습니다." +
                        "2004년부터 현재까지 약 1,700여개 국내 IT기업에 소속된 3,000여명의 전문가가 ICT멘토로 참여하여 매년 1,000여건의 프로젝트를 대학과 같이 진행하고 있으며, " +
                        "현재까지 약 2만여명의 멘티(학생)을 지도하고 있는 미래창조과학부 소속 사업입니다." +
                        "\n\t채용인원" +
                        "\n\t\t00명" +
                        "\n\t채용유형" +
                        "\n\t\tICT 멘토링 홈페이지 관리부 체험형 인턴" +
                        "\n\t접수기한" +
                        "\n\t\t2020년 10월 22일 오후 5시 ~ 2020년 10월 30일 오후 5시" +
                        "\n\t접수방법" +
                        "\n\t\t본 서비스를 통한 온라인 지원" +
                        "\n\t담당업무" +
                        "\n\t\tICT 멘토링 사무국 홈페이지 관리부 소속으로 홈페이지 유지 및 보수 담당" +
                        "\n\t자격요건" +
                        "\n\t\t컴퓨터공학 전공" +
                        "\n\t지원자격" +
                        "\n\t\t2021년 졸업예정자")
                .closedBit(null)
                .question1("회사를 지원하게 된 동기는 무엇인가요? 간단한 자기소개와 함께 작성해 주세요. (1000자 이내)")
                .question2("가장 성취감 느낀 일은 무엇이 있나요? 구체적인 사례를 들어 설명해주세요. (1500자 이내)")
                .question3("입사하게 된다면 가장 하고 싶은 일은 무엇인가요? 구체적인 설명과 함께 작성해주세요. (1500자 이내)")
                .companyInfo(companyInfoSample)
                .companyUser(companyUserSample)
                .build();
        recruitRepository.save(recruitSample);
//        Application applicationSample = Application.builder()
//                .q1Comment("저는 잘할자신이 있어요")
//                .q2Comment("스펙좋아요")
//                .q3Comment("꼭 뽑아주세요")
//                .q1Feedback("굿")
//                .q2Feedback("입")
//                .q3Feedback("니다")
//                .bit(0)
//                .passStage(0)
//                .score(0)
//                .passOrFail(0)
//                .applied(1)
//                .recruit(recruitSample)
//                .user(userSample2)
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
