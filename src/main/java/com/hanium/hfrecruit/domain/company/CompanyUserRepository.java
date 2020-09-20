package com.hanium.hfrecruit.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyUserRepository extends JpaRepository <CompanyUser,Long> {
    @Override
    List<CompanyUser> findAll();

    @Override
    Optional<CompanyUser> findById(Long aLong);

    CompanyUser getOne(Long aLong);

    CompanyUser findByCompanyUserEmail(String email);
}
