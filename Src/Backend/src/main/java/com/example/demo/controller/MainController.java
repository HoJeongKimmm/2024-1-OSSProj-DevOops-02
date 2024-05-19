
package com.example.demo.controller;

import com.example.demo.dto.ProjectDTO;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.ProjectService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    private final UserService userService;
    private final ProjectService projectService;

    @Autowired
    public MainController(UserService userService, ProjectService projectService) {
        this.userService = userService;
        this.projectService = projectService;
    }

    // 애플리케이션의 상태를 확인하는 헬스 체크 엔드포인트
    @GetMapping("/health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("The application is running.");
    }

    // 메인 페이지 또는 기본 정보를 제공하는 엔드포인트
    @GetMapping("/info")
    public ResponseEntity<String> getInfo() {
        return ResponseEntity.ok("Welcome to the Team Matching Service API.");
    }

    // 최근에 생성된 사용자 목록을 제공하는 엔드포인트
    @GetMapping("/recent-users")
    public ResponseEntity<List<UserDTO>> getRecentUsers() {
        List<UserDTO> recentUsers = userService.getAllUsers(); // 필요한 로직을 추가하여 최근 사용자만 가져오도록 변경 가능
        return ResponseEntity.ok(recentUsers);
    }

    // 최근에 생성된 프로젝트 목록을 제공하는 엔드포인트
    @GetMapping("/recent-projects")
    public ResponseEntity<List<ProjectDTO>> getRecentProjects() {
        List<ProjectDTO> recentProjects = projectService.getAllProjects(); // 필요한 로직을 추가하여 최근 프로젝트만 가져오도록 변경 가능
        return ResponseEntity.ok(recentProjects);
    }
}