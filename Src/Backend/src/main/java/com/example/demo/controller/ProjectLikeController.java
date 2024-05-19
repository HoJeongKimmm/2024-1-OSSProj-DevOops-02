package com.example.demo.controller;

import com.example.demo.service.ProjectLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/project-likes")
public class ProjectLikeController {

    private final ProjectLikeService projectLikeService;

    @Autowired
    public ProjectLikeController(ProjectLikeService projectLikeService) {
        this.projectLikeService = projectLikeService;
    }

    @PostMapping("/{projectId}/like")
    public ResponseEntity<Void> likeProject(@RequestParam Long userId, @PathVariable Long projectId) {
        if (projectLikeService.likeProject(userId, projectId)) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(409).build(); // Conflict: 이미 좋아요 한 경우
        }
    }

    @DeleteMapping("/{projectId}/unlike")
    public ResponseEntity<Void> unlikeProject(@RequestParam Long userId, @PathVariable Long projectId) {
        if (projectLikeService.unlikeProject(userId, projectId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build(); // 좋아요가 없는 경우
        }
    }

    @GetMapping("/{projectId}/count")
    public ResponseEntity<Long> countLikes(@PathVariable Long projectId) {
        return ResponseEntity.ok(projectLikeService.countLikesByProjectId(projectId));
    }

    @GetMapping("/{projectId}/is-liked")
    public ResponseEntity<Boolean> isUserLikedProject(@RequestParam Long userId, @PathVariable Long projectId) {
        return ResponseEntity.ok(projectLikeService.hasUserLikedProject(userId, projectId));
    }
}