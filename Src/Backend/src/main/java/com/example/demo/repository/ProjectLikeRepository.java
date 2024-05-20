package com.example.demo.repository;

import com.example.demo.domain.ProjectLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
}