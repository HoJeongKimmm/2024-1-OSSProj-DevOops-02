package com.example.demo.service;

import com.example.demo.domain.Project;
import com.example.demo.domain.Status;
import com.example.demo.domain.User;
import com.example.demo.dto.StatusDTO;
import com.example.demo.mapper.StatusMapper;
import com.example.demo.repository.ProjectRepository;
import com.example.demo.repository.StatusRepository;
import com.example.demo.repository.UserRepository;
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
    private final ProjectRepository projectRepository;
    private final UserRepository userRepository;

    @Autowired
    public StatusService(StatusRepository statusRepository, StatusMapper statusMapper,
                         ProjectRepository projectRepository, UserRepository userRepository) {
        this.statusRepository = statusRepository;
        this.statusMapper = statusMapper;
        this.projectRepository = projectRepository;
        this.userRepository = userRepository;
    }

    public List<StatusDTO> getAllStatuses() {
        return statusRepository.findAll().stream()
                .map(statusMapper::statusToStatusDTO)
                .collect(Collectors.toList());
    }

    public StatusDTO getStatusById(Long id) {
        Status status = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));
        return statusMapper.statusToStatusDTO(status);
    }

    public StatusDTO createStatus(StatusDTO statusDTO) {
        Status status = statusMapper.statusDTOToStatus(statusDTO);
        status = statusRepository.save(status);
        return statusMapper.statusToStatusDTO(status);
    }

    public StatusDTO updateStatus(Long id, StatusDTO statusDTO) {
        Status existingStatus = statusRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Status not found"));

        Project project = projectRepository.findById(statusDTO.getProjectId())
                .orElseThrow(() -> new RuntimeException("Project not found"));
        User user = userRepository.findById(statusDTO.getUserId())
                .orElseThrow(() -> new RuntimeException("User not found"));

        existingStatus.setProject(project);
        existingStatus.setUser(user);
        existingStatus.setState(statusDTO.getState());
        existingStatus.setPosition(statusDTO.getPosition());

        existingStatus = statusRepository.save(existingStatus);
        return statusMapper.statusToStatusDTO(existingStatus);
    }

    public void deleteStatus(Long id) {
        statusRepository.deleteById(id);
    }
}