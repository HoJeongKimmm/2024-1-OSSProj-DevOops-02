package com.example.demo.controller;

import com.example.demo.dto.ProjectLikeDTO;
import com.example.demo.service.ProjectLikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/project-likes")
public class ProjectLikeController {
    private final ProjectLikeService projectLikeService;

    @Autowired
    public ProjectLikeController(ProjectLikeService projectLikeService) {
        this.projectLikeService = projectLikeService;
    }

    @GetMapping
    public List<ProjectLikeDTO> getAllProjectLikes() {
        return projectLikeService.getAllProjectLikes();
    }

    @GetMapping("/{id}")
    public ProjectLikeDTO getProjectLikeById(@PathVariable Long id) {
        return projectLikeService.getProjectLikeById(id);
    }

    @PostMapping
    public ProjectLikeDTO createProjectLike(@RequestBody ProjectLikeDTO projectLikeDTO) {
        return projectLikeService.createProjectLike(projectLikeDTO);
    }

    @PutMapping("/{id}")
    public ProjectLikeDTO updateProjectLike(@PathVariable Long id, @RequestBody ProjectLikeDTO projectLikeDTO) {
        return projectLikeService.updateProjectLike(id, projectLikeDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProjectLike(@PathVariable Long id) {
        projectLikeService.deleteProjectLike(id);
    }

    // DB 연결 테스트용 엔드포인트
    @GetMapping("/test")
    public String testDBConnection() {
        try {
            boolean isConnected = projectLikeService.testConnection();
            return isConnected ? "DB 연결 성공" : "DB 연결 실패";
        } catch (Exception e) {
            return "DB 연결 실패: " + e.getMessage();
        }
    }
}