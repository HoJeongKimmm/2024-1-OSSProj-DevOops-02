package com.example.demo.dto;

import java.util.Date;
import lombok.Data;

@Data
public class CourseDTO {
    private Long id;
    private String title;
    private String description;
    private Date startDate;
    private Date endDate;
    private boolean isActive;
}