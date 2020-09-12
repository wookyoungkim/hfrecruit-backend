package com.hanium.hfrecruit.domain.application;

import com.hanium.hfrecruit.domain.recruit.Recruit;
import com.hanium.hfrecruit.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ApplicationRepository extends JpaRepository <Application, Long> {


    List<Application> findAllByUser(User user);

    Optional<Application> findByApplicationId(Long applicationId);

    Application findByUserAndRecruit(User user, Recruit recruit);
}
