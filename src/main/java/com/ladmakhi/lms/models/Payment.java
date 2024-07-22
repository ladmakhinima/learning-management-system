package com.ladmakhi.lms.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.lms.common.entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "payments")
public class Payment extends CoreEntity {
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "payment_course_mapping",
            joinColumns = @JoinColumn(name = "payment_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    private List<Course> courses;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    @JsonManagedReference
    private User user;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @Column(name = "price")
    private Double price;

    @Column(name = "fee")
    private Double fee;

    @Column(name = "track_id")
    private Long trackId;

    @Column(name = "payed_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date payedAt;

    @Column(name = "expired_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date expiredAt;

    @Column(name = "verified_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date verifiedAt;

    @Column(name = "pay_link")
    private String payLink;

    @OneToOne(cascade = CascadeType.ALL, mappedBy = "payment")
    private Transaction transaction;
}
