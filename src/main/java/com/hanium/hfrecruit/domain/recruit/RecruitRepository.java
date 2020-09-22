package com.hanium.hfrecruit.domain.recruit;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository <Recruit,Long> {
    Optional<Recruit> findByCompanyInfo(CompanyInfo companyinfo);

    Optional<Recruit> findById(Long aLong);

    @Query("SELECT p FROM Recruit p ORDER BY p.id")
    List<Recruit> findAll();

    @Query(
            value = "SELECT r FROM Recruit r WHERE r.recruitTitle LIKE %:keyword% OR r.companyInfo.companyName LIKE %:keyword% OR r.recruitContent LIKE %:keyword%",
            countQuery = "SELECT COUNT(r.recruitNo) FROM Recruit r WHERE r.recruitTitle LIKE %:keyword% OR r.companyInfo.companyName LIKE %:keyword% OR r.recruitContent LIKE %:keyword%"
    )
    List<Recruit> findAllByCompanyInfoCompanyNameOrRecruitContentOrRecruitTitleContaining(String keyword);

    List<Recruit> findAllByCompanyInfo(CompanyInfo companyInfo);

    Optional<Recruit> findByRecruitNo(Long RecruitNo);

    Recruit getOne(Long aLong);
}
