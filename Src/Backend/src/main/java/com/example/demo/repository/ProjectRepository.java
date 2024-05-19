package com.example.demo.repository;
import com.example.demo.domain.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    List<Project> findByCreatedAtBetween(Timestamp startDate, Timestamp endDate);
    List<Project> findByStartDateBetween(Date startDate, Date endDate);
    List<Project> findByEndDateBetween(Date startDate, Date endDate);
    List<Project> findByIsAvailable(boolean isAvailable);
}