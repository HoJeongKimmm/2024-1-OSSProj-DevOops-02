package com.example.demo.mapper;

import com.example.demo.domain.Status;
import com.example.demo.dto.StatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface StatusMapper {
    @Mapping(source = "projectId", target = "project.id")
    @Mapping(source = "userId", target = "user.id")
    Status statusDTOToStatus(StatusDTO statusDTO);

    @Mapping(source = "project.id", target = "projectId")
    @Mapping(source = "user.id", target = "userId")
    StatusDTO statusToStatusDTO(Status status);
}