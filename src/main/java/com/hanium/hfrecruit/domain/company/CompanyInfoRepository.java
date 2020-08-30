package com.hanium.hfrecruit.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyInfoRepository extends JpaRepository <CompanyInfo,Long> {

    Optional<CompanyInfo> findByCompanyNo (Long companyNo);

}
