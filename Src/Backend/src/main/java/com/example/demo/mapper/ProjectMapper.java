package com.example.demo.mapper;

import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    ProjectDTO projectToProjectDTO(Project project);
    Project projectDTOToProject(ProjectDTO projectDTO);
    void updateProjectFromDTO(ProjectDTO projectDTO, @MappingTarget Project project);
}