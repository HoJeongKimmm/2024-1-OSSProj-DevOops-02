package com.example.demo.repository;

import com.example.demo.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByStartDateBetween(Date startDate, Date endDate);
    List<Course> findByEndDateBetween(Date startDate, Date endDate);
}