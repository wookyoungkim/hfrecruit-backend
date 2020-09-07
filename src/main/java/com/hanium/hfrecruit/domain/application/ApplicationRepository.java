package com.hanium.hfrecruit.domain.application;

import com.hanium.hfrecruit.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationRepository extends JpaRepository <Application, Long> {


    List<Application> findAllByUser(User user);
}
