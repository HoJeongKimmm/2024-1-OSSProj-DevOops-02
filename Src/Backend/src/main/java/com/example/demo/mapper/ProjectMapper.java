package com.example.demo.mapper;
import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface ProjectMapper {
    ProjectDTO projectToProjectDTO(Project project);
    Project projectDTOToProject(ProjectDTO projectDTO);
    void updateProjectFromDTO(ProjectDTO projectDTO, @MappingTarget Project project);
}