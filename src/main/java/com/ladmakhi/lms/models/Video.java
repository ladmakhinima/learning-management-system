package com.ladmakhi.lms.models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ladmakhi.lms.common.entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;


@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "videos")
public class Video extends CoreEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    @JsonBackReference
    private Course course;
    private String title;
    private String description;
    private String videoUrl;
    // TODO CALCULATE VIDEO DURATION
}
