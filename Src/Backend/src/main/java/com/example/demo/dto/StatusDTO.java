package com.example.demo.dto;

import lombok.Data;

@Data
public class StatusDTO {
    private Long id;
    private Long projectId;
    private Long userId;
    private String state;
    private String position;
}