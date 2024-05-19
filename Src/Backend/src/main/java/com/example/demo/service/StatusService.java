package com.example.demo.service;

import com.example.demo.domain.Status;
import com.example.demo.dto.StatusDTO;
import com.example.demo.mapper.StatusMapper;
import com.example.demo.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StatusService {
    private final StatusRepository statusRepository;
    private final StatusMapper statusMapper;

    @Autowired
    public StatusService(StatusRepository statusRepository, StatusMapper statusMapper) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
    }

    public StatusDTO getStatusById(Long statusId) {
        Status status = statusRepository.findById(statusId)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        return statusMapper.statusToStatusDTO(status);
    }

    public StatusDTO createStatus(StatusDTO statusDto) {
        Status status = statusMapper.statusDTOToStatus(statusDto);
        status = statusRepository.save(status);
        return statusMapper.statusToStatusDTO(status);
    }

    public StatusDTO updateStatus(StatusDTO statusDto) {
        Status existingStatus = statusRepository.findById(statusDto.getId())
                .orElseThrow(() -> new RuntimeException("Status not found"));
        statusMapper.updateStatusFromDTO(statusDto, existingStatus);
        existingStatus = statusRepository.save(existingStatus);
        return statusMapper.statusToStatusDTO(existingStatus);
    }

    public void deleteStatus(Long statusId) {
        statusRepository.deleteById(statusId);
    }

    public List<StatusDTO> getStatusesByUserId(Long userId) {
        List<Status> statuses = statusRepository.findByUserId(userId);
        return statuses.stream()
                .map(statusMapper::statusToStatusDTO)
                .collect(Collectors.toList());
    }

    public List<StatusDTO> getStatusesByProjectId(Long projectId) {
        List<Status> statuses = statusRepository.findByProjectId(projectId);
        return statuses.stream()
                .map(statusMapper::statusToStatusDTO)
                .collect(Collectors.toList());
    }
}