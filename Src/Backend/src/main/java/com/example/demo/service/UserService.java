package com.example.demo.service;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.mapper.UserMapper;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    public UserService(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    public UserDTO getUserById(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return userMapper.userToUserDTO(user);
    }

    public UserDTO createUser(UserDTO userDto) {
        User user = userMapper.userDTOToUser(userDto);
        user = userRepository.save(user);
        return userMapper.userToUserDTO(user);
    }

    public UserDTO updateUser(UserDTO userDto) {
        User existingUser = userRepository.findById(userDto.getId())
                .orElseThrow(() -> new RuntimeException("User not found"));
        userMapper.updateUserFromDTO(userDto, existingUser);
        existingUser = userRepository.save(existingUser);
        return userMapper.userToUserDTO(existingUser);
    }

    public void deleteUser(Long userId) {
        userRepository.deleteById(userId);
    }

    public List<UserDTO> getAllUsers() {
        return userRepository.findAll().stream()
                .map(userMapper::userToUserDTO)
                .collect(Collectors.toList());
    }

    public UserDTO loginUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user != null && user.getPassword().equals(password)) {
            return userMapper.userToUserDTO(user);
        } else {
            throw new RuntimeException("Invalid email or password");
        }
    }

    public void logoutUser(Long userId) {
        // 로그아웃 관련 로직을 구현합니다. 예: 세션 무효화
    }

    public List<UserDTO> getUsersByProjectId(Long projectId) {
        // 프로젝트 ID로 사용자 목록을 가져오는 로직을 구현합니다.
        return null; // 실제 구현 필요
    }


}