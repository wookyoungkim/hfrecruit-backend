package com.hanium.hfrecruit.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUserNo(long userNo);

    Optional<User> findByEmail(String email);
}
