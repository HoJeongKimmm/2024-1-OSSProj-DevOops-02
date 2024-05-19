package com.example.demo.dto;

import java.util.Date;
import lombok.Data;

@Data
public class ProjectDTO {
    private Long id;
    private String title;
    private String content;
    private Date startDate;
    private Date endDate;
    private boolean isAvailable;
    private Integer pm;
    private String skill;
    private String etc;
}