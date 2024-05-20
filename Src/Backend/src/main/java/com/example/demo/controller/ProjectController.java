package com.example.demo.controller;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/projects")
public class ProjectController {
    private final ProjectService projectService;

    @Autowired
    public ProjectController(ProjectService projectService) {
        this.projectService = projectService;
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

    @GetMapping("/{id}")
    public ProjectDTO getProjectById(@PathVariable Long id) {
        return projectService.getProjectById(id);
    }

    @PostMapping
    public ProjectDTO createProject(@RequestBody ProjectDTO projectDTO) {
        return projectService.createProject(projectDTO);
    }

    @PutMapping("/{id}")
    public ProjectDTO updateProject(@PathVariable Long id, @RequestBody ProjectDTO projectDTO) {
        return projectService.updateProject(id, projectDTO);
    }

    @DeleteMapping("/{id}")
    public void deleteProject(@PathVariable Long id) {
        projectService.deleteProject(id);
    }

    // DB 연결 테스트용 엔드포인트
    @GetMapping("/test")
    public String testDBConnection() {
        try {
            boolean isConnected = projectService.testConnection();
            return isConnected ? "DB 연결 성공" : "DB 연결 실패";
        } catch (Exception e) {
            return "DB 연결 실패: " + e.getMessage();
        }
    }
}