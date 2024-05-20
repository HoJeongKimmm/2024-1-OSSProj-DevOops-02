package com.example.demo.mapper;

import com.example.demo.domain.Project;
import com.example.demo.dto.ProjectDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface ProjectMapper {
    @Mapping(source = "classId", target = "projectClass.id")
    Project projectDTOToProject(ProjectDTO projectDTO);

    @Mapping(source = "projectClass.id", target = "classId")
    ProjectDTO projectToProjectDTO(Project project);

    @Mapping(source = "classId", target = "projectClass.id")
    void updateProjectFromDTO(ProjectDTO projectDTO, @MappingTarget Project project);
}