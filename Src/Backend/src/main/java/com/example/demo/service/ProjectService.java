package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectService {
    private final ProjectRepository projectRepository;
    private final ProjectMapper projectMapper;

    @Autowired
    public ProjectService(ProjectRepository projectRepository, ProjectMapper projectMapper) {
        this.projectRepository = projectRepository;
        this.projectMapper = projectMapper;
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::projectToProjectDTO)
                .collect(Collectors.toList());
    }

    public ProjectDTO getProjectById(Long id) {
        Project project = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.projectToProjectDTO(project);
    }

    public ProjectDTO createProject(ProjectDTO projectDTO) {
        Project project = projectMapper.projectDTOToProject(projectDTO);
        project = projectRepository.save(project);
        return projectMapper.projectToProjectDTO(project);
    }

    public ProjectDTO updateProject(Long id, ProjectDTO projectDTO) {
        Project existingProject = projectRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectMapper.updateProjectFromDTO(projectDTO, existingProject);
        existingProject = projectRepository.save(existingProject);
        return projectMapper.projectToProjectDTO(existingProject);
    }

    public void deleteProject(Long id) {
        projectRepository.deleteById(id);
    }

    public boolean testConnection() {
        return projectRepository.count() >= 0; // 그냥 연결 확인용으로 count 쿼리 사용
    }
}