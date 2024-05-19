package com.example.demo.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String nickname;
    private String password;
    private byte[] profile;
    private String introduce;
    private String email;
    private String stacks;
    private String githubNickname;
    private String resume;
}