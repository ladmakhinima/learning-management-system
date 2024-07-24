package com.ladmakhi.lms.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ladmakhi.lms.common.entity.CoreEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "transaction_reports")
public class TransactionReport extends CoreEntity {
    @Column(name = "year")
    private int year;

    @Column(name = "month")
    private int month;

    @Column(name = "time")
    @Temporal(TemporalType.TIME)
    private Date time;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonManagedReference
    private User user;

    @Column(name = "gateway")
    private TransactionGatewayType gateway;

    @Column(name = "final_price")
    private int finalPrice;

    @Column(name = "final_discount")
    private int finalDiscount;

    @Column(name = "submit_at")
    private Date submitAt;

    @Column(name = "day_of_week")
    private int dayOfWeek;

    @Column(name = "season")
    @Enumerated(EnumType.ORDINAL)
    private SeasonType season;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "course_transaction_mapping",
            inverseJoinColumns = @JoinColumn(name = "course_id"),
            joinColumns = @JoinColumn(name = "transaction_id")
    )
    @JsonManagedReference
    private List<Course> courses;

    @OneToOne
    @JoinColumn(name = "transaction_id")
    @JsonManagedReference
    private Transaction transaction;
}
