package com.example.demo.mapper;

import com.example.demo.domain.ProjectLike;
import com.example.demo.dto.ProjectLikeDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProjectLikeMapper {
    ProjectLikeMapper INSTANCE = Mappers.getMapper(ProjectLikeMapper.class);

    ProjectLikeDTO projectLikeToProjectLikeDTO(ProjectLike projectLike);
    ProjectLike projectLikeDTOToProjectLike(ProjectLikeDTO projectLikeDTO);
}