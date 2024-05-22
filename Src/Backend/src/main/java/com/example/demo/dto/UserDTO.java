package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String nickname;
    private String password;
    private String email;
    private String stacks;
    private String github_nickname;
    private String resume;
    private String introduce;
}