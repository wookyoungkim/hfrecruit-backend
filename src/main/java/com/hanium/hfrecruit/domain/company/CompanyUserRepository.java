package com.hanium.hfrecruit.domain.company;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyUserRepository extends JpaRepository <CompanyUser,Long> {
    @Override
    List<CompanyUser> findAll();
}
