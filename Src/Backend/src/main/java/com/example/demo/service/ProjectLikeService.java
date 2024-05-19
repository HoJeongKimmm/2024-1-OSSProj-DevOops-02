package com.example.demo.service;
import com.example.demo.domain.Project;
import com.example.demo.domain.ProjectLike;
import com.example.demo.domain.User;
import com.example.demo.repository.ProjectLikeRepository;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Optional;

@Service
@Transactional
public class ProjectLikeService {
    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProjectLikeService(ProjectLikeRepository projectLikeRepository, ProjectRepository projectRepository, UserRepository userRepository) {
        this.projectLikeRepository = projectLikeRepository;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public boolean likeProject(Long userId, Long projectId) {
        if (projectLikeRepository.existsByProjectIdAndUserId(projectId, userId)) {
            return false;  // 이미 좋아요가 되어 있으면 추가하지 않음
        }
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new IllegalArgumentException("Project not found"));

        ProjectLike newLike = new ProjectLike();
        newLike.setProject(project);
        newLike.setUser(user);
        projectLikeRepository.save(newLike);
        return true;  // 새로운 좋아요 추가
    }

    public boolean unlikeProject(Long userId, Long projectId) {
        Optional<ProjectLike> projectLike = projectLikeRepository.findByProjectIdAndUserId(projectId, userId);
        if (projectLike.isPresent()) {
            projectLikeRepository.delete(projectLike.get());
            return true;  // 좋아요 삭제
        }
        return false;  // 좋아요가 없는 경우, 삭제 실패
    }

    public long countLikesByProjectId(Long projectId) {
        return projectLikeRepository.countByProjectId(projectId);
    }

    public boolean hasUserLikedProject(Long userId, Long projectId) {
        return projectLikeRepository.existsByProjectIdAndUserId(projectId, userId);
    }
}