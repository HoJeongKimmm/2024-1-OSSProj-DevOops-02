package com.example.demo.mapper;

import com.example.demo.domain.ProjectLike;
import com.example.demo.dto.ProjectLikeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ProjectLikeMapper {
    @Mapping(source = "projectId", target = "project.id")
    @Mapping(source = "userId", target = "user.id")
    ProjectLike projectLikeDTOToProjectLike(ProjectLikeDTO projectLikeDTO);

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "user.id", target = "userId")
    ProjectLikeDTO projectLikeToProjectLikeDTO(ProjectLike projectLike);
}