package com.example.demo.domain;

import javax.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@Entity
@Table(name = "course")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String title;

    @Column(nullable = true)
    private Date startDate;

    @Column(nullable = true)
    private Date endDate;
}