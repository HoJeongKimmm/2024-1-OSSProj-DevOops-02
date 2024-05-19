package com.example.demo.repository;

import com.example.demo.domain.ProjectLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface ProjectLikeRepository extends JpaRepository<ProjectLike, Long> {
    // 특정 프로젝트에 대한 좋아요 수를 반환
    long countByProjectId(Long projectId);

    // 사용자가 특정 프로젝트에 좋아요를 했는지 여부를 확인
    boolean existsByProjectIdAndUserId(Long projectId, Long userId);

    // 사용자의 특정 프로젝트에 대한 좋아요 객체를 찾기
    Optional<ProjectLike> findByProjectIdAndUserId(Long projectId, Long userId);

    // 좋아요 삭제를 위해 특정 좋아요 객체를 찾기
    void deleteByProjectIdAndUserId(Long projectId, Long userId);
}