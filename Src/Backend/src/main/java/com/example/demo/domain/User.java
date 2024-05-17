package com.example.demo.domain;

import com.example.demo.domain.stacks.NodeJs;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.File;
import java.io.Serializable;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nickname;
    private String password;
    private File profile;
    private String introduce;
    private String email;
    private String stacks;
    private String githubId;
    // resume 추가
    private String resume;

    @JsonManagedReference(value = "user_invitation")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Invitation> invitations;

    @JsonManagedReference(value = "user_member")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectMember> project_members;

    @JsonManagedReference(value = "user_like")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<ProjectLike> project_likes;

    @JsonManagedReference(value = "user_apply")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Apply> applys;

    @JsonManagedReference(value = "user_resume")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Resume> resumes;

//    @JsonManagedReference(value = "user_status")
//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
//    private List<Status> statuses;

}

