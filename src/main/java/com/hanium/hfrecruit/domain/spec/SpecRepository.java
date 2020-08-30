package com.hanium.hfrecruit.domain.spec;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SpecRepository extends JpaRepository<Spec,Long> {

    Optional<Spec> findByspecId(Long specId);
}
