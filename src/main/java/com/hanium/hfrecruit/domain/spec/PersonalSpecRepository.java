package com.hanium.hfrecruit.domain.spec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PersonalSpecRepository extends JpaRepository <PersonalSpec,Long> {

    Optional<PersonalSpec> findByPersonalSpecId(Long PersonalSpecId);
}
