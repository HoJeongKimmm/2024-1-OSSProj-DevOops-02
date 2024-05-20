package com.example.demo.controller;

import com.example.demo.domain.User;
import com.example.demo.dto.UserDTO;
import com.example.demo.service.UserService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/join")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDto) {
        UserDTO createdUser = userService.createUser(userDto);
        return ResponseEntity.status(201).body(createdUser);
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> loginUser(@RequestBody UserDTO userDto) {
        UserDTO loggedInUser = userService.loginUser(userDto.getEmail(), userDto.getPassword());
        return ResponseEntity.ok(loggedInUser);
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logoutUser(@RequestParam Long userId) {
        userService.logoutUser(userId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/info")
    public ResponseEntity<UserDTO> getUserById(@RequestParam Long id) {
        UserDTO userDTO = userService.getUserById(id);
        return ResponseEntity.ok(userDTO);
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/project/manage/list")
    public ResponseEntity<List<UserDTO>> getUsersByProjectId(@RequestParam Long projectId) {
        List<UserDTO> users = userService.getUsersByProjectId(projectId);
        return ResponseEntity.ok(users);
    }




}