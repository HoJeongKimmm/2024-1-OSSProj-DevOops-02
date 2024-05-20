package com.example.demo.service;

import com.example.demo.domain.ProjectLike;
import com.example.demo.dto.ProjectLikeDTO;
import com.example.demo.mapper.ProjectLikeMapper;
import com.example.demo.repository.ProjectLikeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ProjectLikeService {
    private final ProjectLikeRepository projectLikeRepository;
    private final ProjectLikeMapper projectLikeMapper;

    @Autowired
    public ProjectLikeService(ProjectLikeRepository projectLikeRepository, ProjectLikeMapper projectLikeMapper) {
        this.projectLikeRepository = projectLikeRepository;
        this.projectLikeMapper = projectLikeMapper;
    }

    public List<ProjectLikeDTO> getAllProjectLikes() {
        return projectLikeRepository.findAll().stream()
                .map(projectLikeMapper::projectLikeToProjectLikeDTO)
                .collect(Collectors.toList());
    }

    public ProjectLikeDTO getProjectLikeById(Long id) {
        ProjectLike projectLike = projectLikeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("ProjectLike not found"));
        return projectLikeMapper.projectLikeToProjectLikeDTO(projectLike);
    }

    public ProjectLikeDTO createProjectLike(ProjectLikeDTO projectLikeDTO) {
        ProjectLike projectLike = projectLikeMapper.projectLikeDTOToProjectLike(projectLikeDTO);
        projectLike = projectLikeRepository.save(projectLike);
        return projectLikeMapper.projectLikeToProjectLikeDTO(projectLike);
    }

    public void deleteProjectLike(Long id) {
        projectLikeRepository.deleteById(id);
    }
}