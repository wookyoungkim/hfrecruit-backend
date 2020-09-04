package com.hanium.hfrecruit.repository;

import com.hanium.hfrecruit.HfrecruitApplicationTests;
import com.hanium.hfrecruit.domain.user.Role;
import com.hanium.hfrecruit.domain.user.User;
import com.hanium.hfrecruit.domain.user.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.transaction.Transactional;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Slf4j
public class UserRepositoryTest extends HfrecruitApplicationTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void create(){
        User user = new User();
        Role role = Role.USER;

        user.setUsername("testuser03");
        user.setCollege("test3");
        user.setEmail("test3");
        user.setBirth("980903");
        user.setGender(1);
        user.setRole(role);

        User newUser = userRepository.save(user);

        System.out.println("newUser: " + newUser);

    }

    @Test
    @Transactional
    public void read(){
        User user = userRepository.findUserByUserNo((1L));
        Optional<User> users = Optional.ofNullable(user);
        users.ifPresent(user1 ->{
            System.out.println("newUser : " + user1);
        });
    }

    @Test
    public void update(){
        User user = userRepository.findUserByUserNo((2L));
        Optional<User> users = Optional.ofNullable(user);

        users.ifPresent(user1 -> {
            user1.setUsername("updated_user");

            userRepository.save(user1);
        });

    }

    @Test
    public void delete(){
        User user = userRepository.findUserByUserNo((2L));
        Optional<User> users = Optional.ofNullable(user);

        assertTrue(users.isPresent());

        users.ifPresent(user1 -> {
            userRepository.delete(user1);   //delete()는 반환형이 x
        });

        Optional<User> deletedUser = userRepository.findById(1L);

        assertFalse(deletedUser.isPresent());
    }
}
