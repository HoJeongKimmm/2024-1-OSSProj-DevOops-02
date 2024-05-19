package com.example.demo.mapper;

import com.example.demo.domain.Status;
import com.example.demo.dto.StatusDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

@Mapper
public interface StatusMapper {
    StatusDTO statusToStatusDTO(Status status);
    Status statusDTOToStatus(StatusDTO statusDTO);
    void updateStatusFromDTO(StatusDTO statusDTO, @MappingTarget Status status);
}