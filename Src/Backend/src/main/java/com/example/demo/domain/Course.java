package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String title;
    private String start_date;  // 수업 시작 날짜
    private String end_date;    // 수업 종료 날짜


    @JsonManagedReference(value = "course_project")
    @OneToMany(mappedBy = "course", cascade = CascadeType.ALL)
    private List<Project> projects = new ArrayList<>();


}
