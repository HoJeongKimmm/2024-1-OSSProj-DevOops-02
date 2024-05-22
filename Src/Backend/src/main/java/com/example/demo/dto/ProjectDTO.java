package com.example.demo.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProjectDTO {
    private Long id;
    private Long classId;
    private String title;
    private String content;
    private Date createdAt;
    private Date updatedAt;
    private Date startDate;
    private Date endDate;
    private boolean isAvailable;
    private Integer pm;
    private String skill;
    private String etc;
}