package com.hanium.hfrecruit.domain.Recruit;

import com.hanium.hfrecruit.domain.company.CompanyInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RecruitRepository extends JpaRepository <Recruit,Long> {
    Optional<Recruit> findByCompanyInfo(CompanyInfo companyinfo);

}
