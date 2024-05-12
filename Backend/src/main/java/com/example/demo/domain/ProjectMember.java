package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.*;


import javax.persistence.*;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ProjectMember {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonBackReference(value = "project_member")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "project_id")
    private Project project;

    @JsonBackReference(value = "user_member")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String position;
}
