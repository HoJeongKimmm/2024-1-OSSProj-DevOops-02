package com.example.demo.domain;

import javax.persistence.*;
import lombok.Data;

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

    @Column(columnDefinition = "TEXT")
    private String introduce;

    @Column(nullable = false, length = 255)
    private String email;

    @Column(length = 255)
    private String stacks;

    @Column(length = 255)
    private String github_nickname;

    @Column(length = 255)
    private String resume;
}