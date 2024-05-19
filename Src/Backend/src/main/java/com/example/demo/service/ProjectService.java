package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectDTO;
import com.example.demo.mapper.ProjectMapper;
import com.example.demo.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.Date;
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

    public ProjectDTO getProjectById(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));
        return projectMapper.projectToProjectDTO(project);
    }

    public ProjectDTO createProject(ProjectDTO projectDto) {
        Project project = projectMapper.projectDTOToProject(projectDto);
        project = projectRepository.save(project);
        return projectMapper.projectToProjectDTO(project);
    }

    public ProjectDTO updateProject(ProjectDTO projectDto) {
        Project existingProject = projectRepository.findById(projectDto.getId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        projectMapper.updateProjectFromDTO(projectDto, existingProject);
        existingProject = projectRepository.save(existingProject);
        return projectMapper.projectToProjectDTO(existingProject);
    }

    public void deleteProject(Long projectId) {
        projectRepository.deleteById(projectId);
    }

    public List<ProjectDTO> getAllProjects() {
        return projectRepository.findAll().stream()
                .map(projectMapper::projectToProjectDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByCreatedAtBetween(Timestamp startDate, Timestamp endDate) {
        return projectRepository.findByCreatedAtBetween(startDate, endDate).stream()
                .map(projectMapper::projectToProjectDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByStartDateBetween(Date startDate, Date endDate) {
        return projectRepository.findByStartDateBetween(startDate, endDate).stream()
                .map(projectMapper::projectToProjectDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByEndDateBetween(Date startDate, Date endDate) {
        return projectRepository.findByEndDateBetween(startDate, endDate).stream()
                .map(projectMapper::projectToProjectDTO)
                .collect(Collectors.toList());
    }

    public List<ProjectDTO> getProjectsByIsAvailable(boolean isAvailable) {
        return projectRepository.findByIsAvailable(isAvailable).stream()
                .map(projectMapper::projectToProjectDTO)
                .collect(Collectors.toList());
    }
}