package com.example.demo.dto;

import lombok.Data;

@Data
public class ProjectLikeDTO {
    private Long id;
    private Long projectId;
    private Long userId;
    private boolean state;
}