package com.hanium.hfrecruit.domain.application;

import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository <Application, Long> {


    List<Application> findAllByUser(User user);

    List<Application> findAllByRecruit(Recruit recruit);

    Optional<Application> findByApplicationId(Long applicationId);

    Application findByUserAndRecruit(User user, Recruit recruit);

    @Query(
            value = "SELECT a FROM Application a WHERE a.user.userNo = :userNo AND (a.recruit.companyInfo.companyName LIKE %:keyword% OR a.recruit.recruitTitle LIKE %:keyword%) " ,
            countQuery = "SELECT a FROM Application a WHERE a.user.userNo = :userNo AND (a.recruit.companyInfo.companyName LIKE %:keyword% OR a.recruit.recruitTitle LIKE %:keyword%) "
    )
    List<Application> findAllByRecruitCompanyInfoCompanyNameOrRecruitRecruitTitleContaining(Long userNo, String keyword);
}
