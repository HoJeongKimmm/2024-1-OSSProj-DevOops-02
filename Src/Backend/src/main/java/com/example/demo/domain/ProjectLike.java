package com.example.demo.domain;

import javax.persistence.*;
import lombok.*;

@Data
@Entity
@Table(name = "project_like")
public class ProjectLike {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "project_id")
    private Project project;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;
}