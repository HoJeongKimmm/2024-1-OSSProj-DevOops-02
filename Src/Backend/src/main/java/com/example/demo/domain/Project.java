package com.example.demo.domain;

import javax.persistence.*;
import lombok.*;

import java.security.Timestamp;
import java.util.Date;

@Data
@Entity
@Table(name = "project")
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "class_id")
    private Course projectClass;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private Timestamp createdAt;

    @Column(nullable = false)
    private Timestamp updatedAt;

    @Column(nullable = true)
    private Date startDate;

    @Column(nullable = true)
    private Date endDate;

    @Column(nullable = true)
    private boolean isAvailable;

    @Column(nullable = true)
    private Integer pm;

    @Column(nullable = true)
    private String skill;

    @Column(nullable = true)
    private String etc;
}