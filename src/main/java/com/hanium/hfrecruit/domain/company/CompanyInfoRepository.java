package com.hanium.hfrecruit.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyInfoRepository extends JpaRepository <CompanyInfo,Long> {

    CompanyInfo findByCompanyNo (Long companyNo);
}
