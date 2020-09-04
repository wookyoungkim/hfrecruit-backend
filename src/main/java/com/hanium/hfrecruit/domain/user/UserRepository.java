package com.hanium.hfrecruit.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findUserByUserNo(long userNo);
    Optional<User> findByUserNo(Long userNo);
    Optional<User> findByEmail(String email);
//    List로 바꾸면 orElse 오류남.

}
