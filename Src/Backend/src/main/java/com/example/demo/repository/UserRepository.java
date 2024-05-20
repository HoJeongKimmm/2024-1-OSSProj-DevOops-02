package com.example.demo.repository;

import com.example.demo.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);

    User findByNickname(String nickname);


    // Resume 관련 CRUD
    User saveResume(User user);

    User updateResume(User user);

    Optional<User> findById(Long userId);

    void deleteById(Long userId);
}