package com.ladmakhi.lms.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.lms.common.entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "courses")
public class Course extends CoreEntity {
    @OneToMany(
            mappedBy = "course",
            fetch = FetchType.LAZY
    )
    @JsonManagedReference
    private List<Video> videos;

    private String title;

    @OneToMany(mappedBy = "course", fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacher_id")
    private User teacher;

    @Column(name = "image")
    private String image;

    @Column(name = "thumbnail")
    private String thumbnail;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private Double price;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private CourseStatus status;

    @Column(name = "level")
    @Enumerated(EnumType.STRING)
    private CourseLevel level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "category_id")
    private Category category;
}
