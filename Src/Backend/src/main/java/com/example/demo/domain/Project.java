package com.example.demo.domain;

import com.example.demo.domain.position.ProjectBack;
import com.example.demo.domain.position.ProjectEtc;
import com.example.demo.domain.position.ProjectFront;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference(value = "course_project")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    private String title;
    private String content;
    private int like_count;
    private int visited_number;
    private Timestamp created_at;
    public Timestamp getCreatedAt() {
        return created_at;
    }

    private Timestamp updated_at;
    private String project_type;
    private String start_date;
    private String end_date;
    private int location;
    private String is_available;
    private Float avg_score;

    @JsonManagedReference(value = "proj_front")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectFront> projectFronts = new ArrayList<>();

    @JsonManagedReference(value = "proj_back")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectBack> projectBacks = new ArrayList<>();

    @JsonManagedReference(value = "proj_etc")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectEtc> projectEtcs = new ArrayList<>();

    @JsonManagedReference(value = "project_member")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectMember> projectMembers = new ArrayList<>();

    @JsonManagedReference(value = "project_invitation")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Invitation> invitations = new ArrayList<>();

    @JsonManagedReference(value = "project_like")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<ProjectLike> projectLikes = new ArrayList<>();

    @JsonManagedReference(value = "project_apply")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Apply> applies = new ArrayList<>();

    @JsonManagedReference(value = "project_status")
    @OneToMany(mappedBy = "project", cascade = CascadeType.ALL)
    private List<Status> statuses = new ArrayList<>();
    public boolean isEmpty() {
        return false;
    };
}
