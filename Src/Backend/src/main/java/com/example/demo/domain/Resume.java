package com.example.demo.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class Resume {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @JsonBackReference(value = "user_resume")
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String summary;
    private Timestamp created_at;
    public Timestamp getCreatedAt() {
        return created_at;
    }
    private Timestamp updated_at;
    public Timestamp getUpdatedAt() {  return updated_at; }
}
