package com.example.demo.repository;

import com.example.demo.domain.Status;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StatusRepository extends JpaRepository<Status, Long> {
    List<Status> findByUserId(Long userId);
    List<Status> findByProjectId(Long projectId);
}