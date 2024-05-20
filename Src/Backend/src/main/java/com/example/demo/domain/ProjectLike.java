package com.example.demo.domain;

import javax.persistence.*;
import lombok.Data;

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

    @Column(nullable = false)
    private Long userId;
}