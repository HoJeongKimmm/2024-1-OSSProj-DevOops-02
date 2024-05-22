package com.example.demo.domain;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nickname;

    @Column(nullable = false, length = 255)
    private String password;

    @Lob
    private byte[] profile;

    private String introduce;

    @Column(nullable = false, length = 255)
    private String email;

    private String stacks;
    private String github_nickname;
    private String resume;
}